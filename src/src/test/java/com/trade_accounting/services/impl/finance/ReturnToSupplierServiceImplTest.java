package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.ReturnToSupplier;
import com.trade_accounting.models.dto.finance.ReturnToSupplierDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.finance.ReturnToSupplierRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.finance.ReturnToSupplierDtoStubs;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturnToSupplierServiceImplTest {

    @Mock
    ReturnToSupplierRepository returnToSupplierRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    ContractRepository contractRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Spy
    ReturnToSupplierDtoStubs returnToSupplierDtoStubs;

    @InjectMocks
    ReturnToSupplierServiceImpl returnToSupplierService;

    @Test
    void getRepoFilledNotNull() {
        assertNotNull(returnToSupplierRepository);
    }

    @Test
    void getAll_shouldReturnFilledListReturnToSuppliers() {
        when(returnToSupplierRepository.getAll())
                .thenReturn(
                        List.of(ReturnToSupplierDtoStubs.getReturnToSupplierDto(1L),
                                ReturnToSupplierDtoStubs.getReturnToSupplierDto(2L),
                                ReturnToSupplierDtoStubs.getReturnToSupplierDto(3L)));
        List<ReturnToSupplierDto> list = returnToSupplierService.getAll();
        assertEquals(3, list.size());
    }

    @Test
    void getById_shouldReturnFilledReturnToSupplier() {
        Optional<ReturnToSupplier> model = Optional.of(ModelStubs.getReturnToSupplier(1L));
        when(returnToSupplierRepository.findById(anyLong())).thenReturn(model);
        ReturnToSupplierDto dto = returnToSupplierService.getById(1L);
        assertEquals(1L, dto.getId());
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
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        returnToSupplierService.deleteById(anyLong());
        verify(returnToSupplierRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(returnToSupplierRepository.save(any())).thenReturn(ModelStubs.getReturnToSupplier(1L));
        ReturnToSupplierDto dto = returnToSupplierService.create(ReturnToSupplierDtoStubs.getReturnToSupplierDto(1L));
        assertEquals(1, dto.getId());
        verify(returnToSupplierRepository).save(any(ReturnToSupplier.class));
    }

}
