package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import com.trade_accounting.models.dto.warehouse.AcceptanceProductionDto;
import com.trade_accounting.repositories.warehouse.AcceptanceProductionRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.warehouse.AcceptanceProductionDtoStubs;
import com.trade_accounting.Stubs.model.warehouse.AcceptanceProductionModelStubs;
import com.trade_accounting.utils.mapper.warehouse.AcceptanceProductionMapper;
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
class AcceptanceProductionServiceImplTest {
    @Mock
    AcceptanceProductionRepository acceptanceProductionRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    AcceptanceProductionMapper acceptanceProductionMapper;


    @InjectMocks
    AcceptanceProductionServiceImpl acceptanceProductionService;

    @Test
    void getAll_shouldReturnListFilledAcceptanceProductionDto() {
        when(acceptanceProductionRepository.findAll())
                .thenReturn(
                        List.of(
                                AcceptanceProductionModelStubs.getAcceptanceProduction(1L),
                                AcceptanceProductionModelStubs.getAcceptanceProduction(2L),
                                AcceptanceProductionModelStubs.getAcceptanceProduction(3L)
                        )
                );

        List<AcceptanceProductionDto> acceptanceProductions = acceptanceProductionService.getAll();

        assertEquals(3, acceptanceProductions.size());
    }

    @Test
    void getById_shouldReturnFilledAcceptanceProduction() {
        when(acceptanceProductionRepository.getOne(anyLong()))
                .thenReturn(AcceptanceProductionModelStubs.getAcceptanceProduction(1L));

        AcceptanceProductionDto acceptanceProductionDto = acceptanceProductionService.getById(1L);

        assertEquals(1, acceptanceProductionDto.getId());
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
        acceptanceProductionService.deleteById(anyLong());
        verify(acceptanceProductionRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(acceptanceProductionRepository.save(any(AcceptanceProduction.class)))
                .thenReturn(AcceptanceProductionModelStubs.getAcceptanceProduction(1L));

        when(productRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getProduct(1L));

        AcceptanceProductionDto acceptanceProductionDto =
                acceptanceProductionService.create(AcceptanceProductionDtoStubs.getAcceptanceProductionDto(1L));

        assertEquals(1, acceptanceProductionDto.getId());

        verify(acceptanceProductionRepository).save(any(AcceptanceProduction.class));
    }
}
