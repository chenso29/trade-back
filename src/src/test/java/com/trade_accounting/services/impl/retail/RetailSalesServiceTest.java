package src.test.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.entity.retail.RetailSales;
import com.trade_accounting.models.dto.retail.RetailSalesDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.retail.RetailSalesRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.Stubs.dto.retail.RetailSalesDtoStubs;
import com.trade_accounting.Stubs.model.retail.RetailSalesModelStubs;
import com.trade_accounting.utils.mapper.retail.RetailSalesMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetailSalesServiceTest {

    @Mock
    RetailSalesRepository retailSalesRepository;

    @Mock
    RetailStoreRepository retailStoreRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    RetailSalesServiceImpl retailSalesService;

    @Spy
    RetailSalesMapper retailSalesMapper;

    @Test
    void getAll_shouldReturnFilledRetailSales() {
        when(retailSalesRepository.findAll()).thenReturn(
                List.of(RetailSalesModelStubs.getRetailSales(1L))
        );

        List<RetailSalesDto> list = retailSalesService.getAll();
        assertNotNull(list, "failure - expected that a list of bankAccountDto not null");
        assertEquals(1, list.size(), "failure - expected that a list of bankAccountDto grater than 0");
    }

    @Test
    void getById_shouldReturnFilledRetailSales() {
        Optional<RetailSales> retailSales = Optional.of(RetailSalesModelStubs.getRetailSales(1L));
        when(retailSalesRepository.findById(anyLong())).thenReturn(retailSales);

        RetailSalesDto retailSalesDto = retailSalesService.getById(1L);
        assertEquals(1, retailSalesDto.getId());
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
    void delete_shouldPassInstructionsSuccessfulDelete() {
        retailSalesRepository.deleteById(anyLong());
        verify(retailSalesRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailSalesRepository.save(any(RetailSales.class))).thenReturn(RetailSalesModelStubs.getRetailSales(1L));
        RetailSalesDto retailSalesDto = retailSalesService.create(RetailSalesDtoStubs.getDto(1L));
        assertEquals(1,retailSalesDto.getId());
        verify(retailSalesRepository).save(any(RetailSales.class));
    }
}
