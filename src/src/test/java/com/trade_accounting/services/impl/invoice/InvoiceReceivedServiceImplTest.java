package src.test.java.com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.entity.invoice.InvoiceReceived;
import com.trade_accounting.models.dto.invoice.InvoiceReceivedDto;
import com.trade_accounting.repositories.warehouse.AcceptanceRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.invoice.InvoiceReceivedRepository;
import com.trade_accounting.Stubs.model.invoice.InvoiceReceivedModelStubs;
import com.trade_accounting.utils.mapper.invoice.InvoiceReceivedMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceReceivedServiceImplTest {

    @InjectMocks
    private InvoiceReceivedServiceImpl invoiceReceivedService;

    @Mock
    private InvoiceReceivedRepository invoiceReceivedRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ContractorRepository contractorRepository;

    @Mock
    private AcceptanceRepository acceptanceRepository;

    @Spy
    private InvoiceReceivedMapperImpl invoiceReceivedMapper;

    @Test
    void getAll_shouldReturnListInvoiceReceivedDto() {
        when(invoiceReceivedRepository.findAll())
                .thenReturn(
                        Stream.of(
                                InvoiceReceivedModelStubs.getInvoiceReceived(1L),
                                InvoiceReceivedModelStubs.getInvoiceReceived(2L),
                                InvoiceReceivedModelStubs.getInvoiceReceived(3L)
                        ).collect(Collectors.toList())
        );
        List<InvoiceReceivedDto> invoiceReceivedDtos = invoiceReceivedService.getAll();
        assertNotNull(invoiceReceivedDtos, "failure - expected that a list of InvoiceReceivedDto not null");
        assertTrue(invoiceReceivedDtos.size() > 0, "failure - expected that a list of InvoiceReceivedDto grater than 0");
        invoiceReceivedDtos.forEach(this::invoiceReceivedDtosIsCorrectlyInited);
    }

    @Test
    void getAll_shouldReturnEmptyListInvoiceReceivedDto() {
        when(invoiceReceivedRepository.findAll())
                .thenReturn(
                        new ArrayList<>()
                );
        List<InvoiceReceivedDto> invoiceReceivedDtos = invoiceReceivedService.getAll();
        assertNotNull(invoiceReceivedDtos, "failure - expected that a list of InvoiceReceivedDto not null");
        assertEquals(0, invoiceReceivedDtos.size(), "failure - expected that size of list of InvoiceReceivedDto equals 0");
    }

    @Test
    void getById_shouldReturnInvoiceReceivedDto() {
        Optional<InvoiceReceived> invoiceReceivedFromRepo =
                Optional.of(
                        InvoiceReceivedModelStubs.getInvoiceReceived(1L)
                );
        when(invoiceReceivedRepository.findById(anyLong()))
                .thenReturn(invoiceReceivedFromRepo);
        InvoiceReceivedDto invoiceReceivedDto = invoiceReceivedService.getById(1L);
        assertNotNull(invoiceReceivedDto, "failure - expected that invoiceDto not null");
        invoiceReceivedDtosIsCorrectlyInited(invoiceReceivedDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        invoiceReceivedService.create(invoiceReceivedMapper.toDto(InvoiceReceivedModelStubs.getInvoiceReceived(1L)));
        verify(companyRepository).getCompaniesById(1L);
        verify(contractorRepository).getContractorById(1L);
        verify(acceptanceRepository).getOne(1L);
        verify(invoiceReceivedRepository).save(any(InvoiceReceived.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        invoiceReceivedService.update(invoiceReceivedMapper.toDto(InvoiceReceivedModelStubs.getInvoiceReceived(1L)));
        verify(invoiceReceivedMapper).toModel(any(InvoiceReceivedDto.class));
        verify(invoiceReceivedRepository).save(any(InvoiceReceived.class));
        verify(invoiceReceivedMapper).toDto(any(InvoiceReceived.class));
    }

    @Test
    void deleteById() {
        invoiceReceivedService.deleteById(1L);
        verify(invoiceReceivedRepository).deleteById(anyLong());
    }

    private void invoiceReceivedDtosIsCorrectlyInited(InvoiceReceivedDto invoiceReceivedDto) {
        assertNotNull(invoiceReceivedDto, "Fail in passed invoiceReceivedDto");
        assertNotNull(invoiceReceivedDto.getId(), "Fail in field 'id' of invoiceReceivedDto");
    }
}