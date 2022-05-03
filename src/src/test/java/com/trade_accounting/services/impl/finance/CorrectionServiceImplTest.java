package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.Correction;
import com.trade_accounting.models.dto.finance.CorrectionDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.finance.CorrectionProductRepository;
import com.trade_accounting.repositories.finance.CorrectionRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.finance.CorrectionDtoStubs;
import com.trade_accounting.utils.mapper.finance.CorrectionMapper;
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
class CorrectionServiceImplTest {

    @Mock
    CorrectionRepository correctionRepository;

    @Mock
    CorrectionProductRepository correctionProductRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Mock
    CompanyRepository companyRepository;

    @Spy
    CorrectionMapper correctionMapper;

    @InjectMocks
    CorrectionServiceImpl correctionService;

    @Test
    void getAll_shouldReturnFilledListCorrection() {
        when(correctionRepository.getAll()).thenReturn(
                List.of(ModelStubs.getCorrection(1L),
                        ModelStubs.getCorrection(2L),
                        ModelStubs.getCorrection(3L))
        );

        List<CorrectionDto> correctionDtos = correctionService.getAll();

        assertEquals(3, correctionDtos.size());
    }

    @Test
    void getById_shouldReturnFilledCorrection() {
        when(correctionRepository.getCorrectionById(anyLong())).thenReturn(ModelStubs.getCorrection(1L));

        CorrectionDto correctionDto = correctionService.getById(1L);

        assertEquals(1, correctionDto.getId());
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
        correctionService.deleteById(anyLong());
        verify(correctionRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(correctionRepository.save(any(Correction.class))).thenReturn(ModelStubs.getCorrection(1L));
        CorrectionDto correctionDto = correctionService.create(CorrectionDtoStubs.getCorrectionDto(1L));
        assertEquals(1, correctionDto.getId());
        verify(correctionRepository).save(any(Correction.class));
    }
}