package src.test.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.Production;
import com.trade_accounting.models.dto.production.ProductionDto;
import com.trade_accounting.repositories.production.ProductionRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.production.ProductionDtoStubs;
import com.trade_accounting.utils.mapper.production.ProductionMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductionServiceImplTest {

    @InjectMocks
    private ProductionServiceImpl productionService;

    @Mock
    private ProductionRepository productionRepository;

    @Spy
    private ProductionMapperImpl productionMapper;

    @Test
    void getAll_shouldReturnListFilledProductionDto() {
        when(productionRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getProduction(1L),
                                ModelStubs.getProduction(10L),
                                ModelStubs.getProduction(20L)
                        )
                                .collect(Collectors.toList())
                );
        List<ProductionDto> productions = productionService.getAll();

        assertNotNull(productions, "Failure - expected that list of productions not null");
        assertEquals(3, productions.size(), "failure - expected that size of list of productions greater than 0");
        verify(productionRepository).findAll();
    }

    @Test
    void getById_shouldReturnFilledProductionDto() {
        when(productionRepository.findById(anyLong()))
                .thenReturn(ofNullable(ModelStubs.getProduction(1L)));
        ProductionDto productionDto = productionService.getById(anyLong());

        assertNotNull(productionDto, "Failure - expected that list of productions not null");
        verify(productionRepository).findById(anyLong());
    }

    @Test
    void create_shouldPassInstructionSuccessfulCreate() {
        when(productionRepository.save(any(Production.class)))
                .thenReturn(ModelStubs.getProduction(1L));

        productionService.create(
                ProductionDtoStubs.getProductionDto(1L)
        );

        verify(productionRepository).save(any(Production.class));
    }

    @Test
    void update_shouldPassInstructionSuccessfulUpdate() {
        when(productionRepository.save(any(Production.class)))
                .thenReturn(ModelStubs.getProduction(1L));

        productionService.update(
                ProductionDtoStubs.getProductionDto(1L)
        );

        verify(productionRepository).save(any(Production.class));
    }

    @Test
    void deleteById_shouldPassInstructionSuccessfulDelete() {
        productionService.deleteById(1L);
        verify(productionRepository).deleteById(anyLong());
    }
}