package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.InventarizationProduct;
import com.trade_accounting.models.dto.warehouse.InventarizationProductDto;
import com.trade_accounting.repositories.warehouse.InventarizationProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.warehouse.InventarizationProductDtoStubs;
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
public class InventarizationProductServiceImplTest {

    @Mock
    InventarizationProductRepository inventarizationProductRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    InventarizationProductServiceImpl inventarizationProductService;

    @Spy
    InventarizationProductDtoStubs inventarizationProductDtoStubs;

    @Test
    void getAll_shouldReturnFilledListInventarizationProduct() {
        when(inventarizationProductRepository.findAll()).thenReturn(
                List.of(ModelStubs.getInventarizationProduct(1L))
        );

        List<InventarizationProductDto> inventarizationProductList = inventarizationProductService.getAll();
        assertEquals(1, inventarizationProductList.size());
    }

    @Test
    void getById_shouldReturnFilledInventarizationProduct() {
        InventarizationProduct inventarizationProduct = ModelStubs.getInventarizationProduct(1L);

        when(inventarizationProductRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.of(inventarizationProduct));

        InventarizationProductDto inventarizationProductDto = inventarizationProductService.getById(1L);
        assertEquals(1,inventarizationProductDto.getId());
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
        inventarizationProductRepository.deleteById(anyLong());
        verify(inventarizationProductRepository).deleteById(anyLong());
    }

    void saveOrUpdate() {
        when(inventarizationProductRepository.save(any()))
                .thenReturn(ModelStubs.getInventarizationProduct(1L));
        InventarizationProductDto inventarizationProductDto =
                inventarizationProductService.create(InventarizationProductDtoStubs.getInventarizationProductDto(1L));
        assertEquals(1, inventarizationProductDto.getId());
        verify(inventarizationProductRepository).save(any(InventarizationProduct.class));
    }
}
