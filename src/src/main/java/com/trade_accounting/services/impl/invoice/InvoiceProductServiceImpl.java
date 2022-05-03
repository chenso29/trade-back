package src.main.java.com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import com.trade_accounting.repositories.invoice.InvoiceProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.services.interfaces.invoice.InvoiceProductService;
import com.trade_accounting.utils.mapper.invoice.InvoiceProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceProductMapper invoiceProductMapper;
    private final ProductRepository productRepository;

    @Override
    public List<InvoiceProductDto> getAll() {
        List<InvoiceProduct> listInvoiceProductDto = invoiceProductRepository.findAll();
        return listInvoiceProductDto.stream().map(invoiceProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceProductDto> search(Specification<InvoiceProduct> spec) {
        return executeSearch(invoiceProductRepository, invoiceProductMapper::toDto, spec);
    }

    @Override
    public List<InvoiceProductDto> getAllByProductId(Long id) {
        return invoiceProductRepository.getAllByProductId(id).stream().map(invoiceProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public InvoiceProductDto getById(Long id) {
        Optional<InvoiceProduct> invoiceProductDto = invoiceProductRepository.findById(id);
        return invoiceProductMapper.toDto(invoiceProductDto.orElse(new InvoiceProduct()));
    }

    @Override
    public InvoiceProductDto create(@NotNull InvoiceProductDto invoiceProductDto) {
        return saveOrUpdate(invoiceProductDto);
    }

    @Override
    public InvoiceProductDto update(@NotNull InvoiceProductDto invoiceProductDto) {
        return saveOrUpdate(invoiceProductDto);
    }

    @Override
    public void deleteById(Long id) {
        invoiceProductRepository.deleteById(id);
    }

    private InvoiceProductDto saveOrUpdate(InvoiceProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());

        InvoiceProduct invoiceProduct = invoiceProductMapper.toModel(dto);

        invoiceProduct.setProduct(product.orElse(null));

        return invoiceProductMapper.toDto(invoiceProductRepository.save(invoiceProduct));
    }

}
