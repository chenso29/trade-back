package src.test.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.TechnicalCardProduction;
import com.trade_accounting.models.dto.production.TechnicalCardProductionDto;
import com.trade_accounting.repositories.production.TechnicalCardProductionRepository;
import com.trade_accounting.Stubs.model.production.TechnicalCardProductionServiceModelStubs;
import com.trade_accounting.services.interfaces.warehouse.ProductService;
import com.trade_accounting.utils.mapper.warehouse.ProductMapper;
import com.trade_accounting.utils.mapper.production.TechnicalCardProductionMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class TechnicalCardProductionServiceImplTest {


    @InjectMocks
    private TechnicalCardProductionServiceImpl technicalCardProductionService;
    @Mock
    private  TechnicalCardProductionRepository cardProductionRepository;
    @Spy
    private  TechnicalCardProductionMapper cardProductionMapper;
    @Mock
    private  ProductService productService;
    @Spy
    private  ProductMapper productMapper;


    @Test
    void getAll() {
        when(cardProductionRepository.findAll())
                .thenReturn(List.of(TechnicalCardProductionServiceModelStubs.getTechnicalCardProduction(1L),
                        TechnicalCardProductionServiceModelStubs.getTechnicalCardProduction(2L),
                        TechnicalCardProductionServiceModelStubs.getTechnicalCardProduction(3L)
                ));
        List<TechnicalCardProductionDto> technicalCardProductionDtos = technicalCardProductionService.getAll();

        assertEquals(3,technicalCardProductionDtos.size());

    }

    @Test
    void getById() {
        Optional<TechnicalCardProduction> technicalCardProduction =
                Optional.of(
                        TechnicalCardProductionServiceModelStubs.getTechnicalCardProduction(1L)
                );

        when(cardProductionRepository.findById(anyLong())).thenReturn(technicalCardProduction);

        TechnicalCardProductionDto technicalCardProductionDto = technicalCardProductionService.getById(1L);

        technicalCardProductionDtoIsCorrectlyInited(technicalCardProductionDto);

//        assertEquals(1,  technicalCardProductionDto.getId());

    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void finaAllById() {
    }
    private void technicalCardProductionDtoIsCorrectlyInited(TechnicalCardProductionDto technicalCardProductionDto) {
        assertNotNull(technicalCardProductionDto, "failure - fail in passed shipmentProductDto1");
        assertNotNull(technicalCardProductionDto.getId(), "failure - fail in field 'id' into shipmentProductDto");
        assertNotNull(technicalCardProductionDto.getProductId(), "failure - fail in field 'amount' into shipmentProductDto");
        assertNotNull(technicalCardProductionDto.getAmount(), "failure - fail in field 'amount' into shipmentProductDto");
//        assertNotNull(shipmentDto.getContractorId(), "failure - fail in field 'price' into shipmentProductDto");
//        assertNotNull(shipmentDto.getWarehouseId(), "failure - fail in field 'productId' into shipmentProductDto");
//        assertNotNull(shipmentDto.getPaid(), "failure - fail in field 'productId' into shipmentProductDto");


    }

}