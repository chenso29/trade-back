package src.test.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.Stubs.dto.retail.RetailCloudCheckDtoStubs;
import com.trade_accounting.Stubs.model.retail.RetailCloudCheckModelStubs;
import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.retail.RetailCloudCheck;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.retail.RetailCloudCheckRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.utils.mapper.retail.RetailCloudCheckMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RetailCloudCheckServiceImplTest {

    @Mock
    private RetailCloudCheckRepository retailCloudCheckRepository;

    @Mock
    private RetailStoreRepository retailStoreRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private RetailCloudCheckServiceImpl retailCloudCheckService;

    @Spy
    private RetailCloudCheckMapper retailCloudCheckMapper;

    @Test
    void getAll() {
        when(retailCloudCheckRepository.findAll()).thenReturn(
                List.of(
                        RetailCloudCheckModelStubs.getRetailCloudCheckModelStubs(1L),
                        RetailCloudCheckModelStubs.getRetailCloudCheckModelStubs(2L)
                )
        );
        List<RetailCloudCheckDto> list = retailCloudCheckService.getAll();
        assertEquals(2, list.size());
    }

    @Test
    void getById() {
        when(retailCloudCheckRepository.findById(anyLong())).
                thenReturn(Optional.of(RetailCloudCheckModelStubs.getRetailCloudCheckModelStubs(1L)));

        RetailCloudCheckDto retailCloudCheckDto = retailCloudCheckService.getById(1L);

        assertEquals(1, retailCloudCheckDto.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        retailCloudCheckService.deleteById(anyLong());
        verify(retailCloudCheckRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailStoreRepository.findById(anyLong())).thenReturn(Optional.of(new RetailStore()));
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(new Employee()));
        when(retailCloudCheckRepository.save(any(RetailCloudCheck.class))).
                thenReturn(RetailCloudCheckModelStubs.getRetailCloudCheckModelStubs(1L));
        RetailCloudCheckDto retailCloudCheckDto = retailCloudCheckService.create(RetailCloudCheckDtoStubs.getDto(1L));
        assertEquals(1, retailCloudCheckDto.getId());
        verify(retailCloudCheckRepository).save(any(RetailCloudCheck.class));
    }
}
