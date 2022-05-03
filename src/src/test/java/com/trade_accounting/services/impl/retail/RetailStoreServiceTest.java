package src.test.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.retail.RetailStoreDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.Stubs.dto.retail.RetailStoreDtoStubs;
import com.trade_accounting.Stubs.model.retail.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.retail.RetailStoreMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RetailStoreServiceTest {

    @Mock
    RetailStoreRepository retailStoreRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    RetailStoreServiceImpl retailStoreService;

    @Spy
    RetailStoreMapper retailStoreMapper;

    @Test
    void getAll_shouldReturnFilledListRetailStore() {
        when(retailStoreRepository.findAll()).thenReturn(
                List.of(RetailStoreModelStubs.getRetailStore(1L))
        );

        List<RetailStoreDto> listInv = retailStoreService.getAll();
        assertEquals(1, listInv.size());
    }

    @Test
    void getById_shouldReturnFilledRetailStore() {
        when(retailStoreRepository.findById(anyLong())).thenReturn(java.util.Optional.of(RetailStoreModelStubs.getRetailStore(1L)));

        RetailStoreDto retailStoreDto = retailStoreService.getById(1L);
        assertEquals(1, retailStoreDto.getId());
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
        retailStoreRepository.deleteById(anyLong());
        verify(retailStoreRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailStoreRepository.save(any(RetailStore.class))).thenReturn(RetailStoreModelStubs.getRetailStore(1L));
        RetailStoreDto retailStoreDto = retailStoreService.create(RetailStoreDtoStubs.getDto(1L));
        assertEquals(1,retailStoreDto.getId());
        verify(retailStoreRepository).save(any(RetailStore.class));
    }
}
