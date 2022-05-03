package src.test.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.Stubs.dto.retail.RetailShiftDtoStubs;
import com.trade_accounting.Stubs.model.retail.RetailShiftModelStubs;
import com.trade_accounting.models.dto.retail.RetailShiftDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.retail.RetailShift;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.retail.RetailShiftRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.utils.mapper.retail.RetailShiftMapper;
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
public class RetailShiftServiceImplTest {

    @Mock
    private RetailShiftRepository retailShiftRepository;

    @Mock
    private RetailStoreRepository retailStoreRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private RetailShiftServiceImpl retailShiftService;

    @Spy
    private RetailShiftMapper retailShiftMapper;

    @Test
    void getAll() {
        when(retailShiftRepository.findAll()).thenReturn(
                List.of(
                        RetailShiftModelStubs.getRetailShiftModelStubs(1L)
                )
        );
        List<RetailShiftDto> list = retailShiftService.getAll();
        assertEquals(1, list.size());
    }

    @Test
    void getById() {
        when(retailShiftRepository.findById(anyLong())).
                thenReturn(java.util.Optional.of(RetailShiftModelStubs.getRetailShiftModelStubs(1L)));

        RetailShiftDto retailShiftDto = retailShiftService.getById(1L);

        assertEquals(1, retailShiftDto.getId());
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
        retailShiftService.deleteById(anyLong());
        verify(retailShiftRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailStoreRepository.getOne(anyLong())).thenReturn(new RetailStore());
        when(companyRepository.getCompaniesById(anyLong())).thenReturn(new Company());
        when(warehouseRepository.getOne(anyLong())).thenReturn(new Warehouse());
        when(retailShiftRepository.save(any(RetailShift.class))).
                thenReturn(RetailShiftModelStubs.getRetailShiftModelStubs(1L));
        RetailShiftDto retailShiftDtoDto = retailShiftService.create(RetailShiftDtoStubs.getDto(1L));
        assertEquals(1, retailShiftDtoDto.getId());
        verify(retailShiftRepository).save(any(RetailShift.class));
    }
}

