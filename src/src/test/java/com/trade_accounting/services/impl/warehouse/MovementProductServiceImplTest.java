package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.MovementProduct;
import com.trade_accounting.models.dto.warehouse.MovementProductDto;
import com.trade_accounting.repositories.warehouse.MovementProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.Stubs.dto.warehouse.MovementProductDtoStubs;
import com.trade_accounting.Stubs.model.warehouse.MovementProductModelStubs;
import com.trade_accounting.utils.mapper.warehouse.MovementProductMapperImpl;
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
public class MovementProductServiceImplTest {

    @Mock
    MovementProductRepository movementProductRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    MovementProductMapperImpl movementProductMapper;

    @InjectMocks
    MovementProductServiceImpl movementProductService;

    @Test
    void getAll_shouldReturnFilledListMovementProduct() {
        when(movementProductRepository.findAll())
                .thenReturn(List.of(MovementProductModelStubs.getMovementProduct(1L),
                        MovementProductModelStubs.getMovementProduct(2L),
                        MovementProductModelStubs.getMovementProduct(3L)));

        List<MovementProductDto> movementProductDtos = movementProductService.getAll();
        assertEquals(3, movementProductDtos.size());
    }

    @Test
    void getById_shouldReturnFilledMovementProduct() {
        Optional<MovementProduct> movementProduct = Optional.of(MovementProductModelStubs.getMovementProduct(1L));

        when(movementProductRepository.findById(anyLong())).thenReturn(movementProduct);

        MovementProductDto movementProductDto = movementProductService.getById(1L);
        assertEquals(1L, movementProductDto.getId());
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
        movementProductService.deleteById(anyLong());
        verify(movementProductRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(movementProductRepository.save(any())).thenReturn(MovementProductModelStubs.getMovementProduct(1L));
        MovementProductDto movementProductDto = movementProductService.create(MovementProductDtoStubs.getMovementProductDto(1L));
        assertEquals(1, movementProductDto.getId());
        verify(movementProductRepository).save(any(MovementProduct.class));
    }
}
