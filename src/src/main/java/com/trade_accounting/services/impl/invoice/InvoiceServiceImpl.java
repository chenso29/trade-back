package src.main.java.com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.invoice.InvoiceProductRepository;
import com.trade_accounting.repositories.invoice.InvoiceRepository;
import com.trade_accounting.repositories.invoice.InvoicesStatusRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.invoice.InvoiceService;
import com.trade_accounting.utils.mapper.company.CompanyMapper;
import com.trade_accounting.utils.mapper.company.ContractorMapper;
import com.trade_accounting.utils.mapper.invoice.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final WarehouseRepository warehouseRepository;
    private final ContractorMapper contractorMapper;
    private final InvoicesStatusRepository invoicesStatusRepository;
    private final InvoiceProductRepository invoiceProductRepository;
    private final CompanyMapper companyMapper;
    private final InvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceDto> search(Specification<Invoice> specification) {
        return executeSearch(invoiceRepository, invoiceMapper::toDto, specification);
    }


    @Override
    public List<InvoiceDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice) {
        List<InvoiceDto> invoiceDtoList = invoiceRepository.findBySearchAndTypeOfInvoice(search, typeOfInvoice);
        for (InvoiceDto invoice : invoiceDtoList) {
            invoice.setCompanyId(companyMapper.toDto(companyRepository.getCompaniesById(invoice.getCompanyId())).getId());
            invoice.setContractorId(contractorMapper.contractorToContractorDto(
                    contractorRepository.getOne(invoice.getContractorId())).getId());
            invoice.setWarehouseId(warehouseRepository.getById(invoice.getWarehouseId()).getId());

        }
        return invoiceDtoList;
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> getFromDateTime(LocalDateTime dateTime) {
        return invoiceRepository.getFromDateTime(dateTime);
    }

    @Override
    public List<InvoiceDto> getByContractorId(Long id) {
        return invoiceRepository.findByContractorId(id);
    }

    @Override
    public void moveToRecyclebin(long id) {
        Invoice invoice = invoiceRepository.getOne(id);
        invoice.setIsRecyclebin(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        Invoice invoice = invoiceRepository.getOne(id);
        invoice.setIsRecyclebin(false);
        invoiceRepository.save(invoice);
    }

    @Override
    public InvoiceDto getById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoiceMapper.toDto(invoice.orElse(new Invoice()));
    }

    @Override
    public InvoiceDto create(InvoiceDto invoiceDto) {
        Invoice invoiceSaved = invoiceMapper.toModel(invoiceDto);
        Company company = companyRepository.getCompaniesById(invoiceDto.getCompanyId());
        Contractor contractor = contractorRepository.getContractorById(invoiceDto.getContractorId());
        Warehouse warehouse = warehouseRepository.getOne(invoiceDto.getWarehouseId());
        InvoicesStatus invoicesStatus = invoicesStatusRepository.getInvoicesStatusById(invoiceDto.getInvoicesStatusId());
        invoiceSaved.setCompany(company);
        invoiceSaved.setInvoiceProducts(
                invoiceDto.getInvoiceProductsIds().stream()
                        .map(id -> invoiceProductRepository.findById(id).orElse(null))
                        .collect(Collectors.toList()));
        invoiceSaved.setContractor(contractor);
        invoiceSaved.setWarehouse(warehouse);
        invoiceSaved.setInvoicesStatus(invoicesStatus);

        return invoiceMapper.toDto(invoiceRepository.save(invoiceSaved));
    }


    @Override
    public InvoiceDto update(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceRepository.save(invoiceMapper.toModel(invoiceDto));
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
