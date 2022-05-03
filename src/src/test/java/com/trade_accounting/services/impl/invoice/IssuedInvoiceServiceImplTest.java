package src.test.java.com.trade_accounting.services.impl.invoice;


import com.trade_accounting.models.entity.invoice.IssuedInvoice;
import com.trade_accounting.models.dto.invoice.IssuedInvoiceDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.invoice.IssuedInvoiceRepository;
import com.trade_accounting.Stubs.dto.invoice.IssuedInvoiceDtoStubs;
import com.trade_accounting.Stubs.model.invoice.IssuedInvoiceModelStubs;
import com.trade_accounting.utils.mapper.invoice.IssuedInvoiceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IssuedInvoiceServiceImplTest {

    @Mock
    IssuedInvoiceRepository issuedInvoiceRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    IssuedInvoiceServiceImpl issuedInvoiceService;

    @Spy
    IssuedInvoiceMapper issuedInvoiceMapper;

    @Test
    void getAll() {
        when(issuedInvoiceRepository.findAll()).thenReturn(
                List.of(IssuedInvoiceModelStubs.getIssuedInvoice(1L))
        );

        List<IssuedInvoiceDto> listInv = issuedInvoiceService.getAll();
        assertEquals(1, listInv.size());
    }

    @Test
    void getById() {
        when(issuedInvoiceRepository.findById(anyLong())).thenReturn(of(IssuedInvoiceModelStubs.getIssuedInvoice(1L)));

        IssuedInvoiceDto issuedInvoiceDto = issuedInvoiceService.getById(1L);
        assertEquals(1, issuedInvoiceDto.getId());
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        saveOrUpdate();
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        saveOrUpdate();
    }

    @Test
    void delete_update_shouldPassInstructionsSuccessfulDelete() {
        issuedInvoiceRepository.deleteById(anyLong());
        verify(issuedInvoiceRepository).deleteById(anyLong());
    }


    private void saveOrUpdate() {
        when(issuedInvoiceRepository.save(any(IssuedInvoice.class))).thenReturn(IssuedInvoiceModelStubs.getIssuedInvoice(1L));
        IssuedInvoiceDto issuedInvoiceDto = issuedInvoiceService.create(IssuedInvoiceDtoStubs.getDto(1L));
        assertEquals(1,issuedInvoiceDto.getId());
        verify(issuedInvoiceRepository).save(any(IssuedInvoice.class));
    }
}
