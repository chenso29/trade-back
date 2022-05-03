package src.test.java.com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import com.trade_accounting.models.dto.invoice.InternalOrderProductsDto;
import com.trade_accounting.repositories.invoice.InternalOrderProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.Stubs.dto.invoice.InternalOrderProductDtoStubs;
import com.trade_accounting.Stubs.model.invoice.InternalOrderProductModelStubs;
import com.trade_accounting.utils.mapper.invoice.InternalOrderProductMapper;
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
public class InternalOrderProductServiceImplTest {
    @InjectMocks
    private InternalOrderProductServiceImpl internalOrderProductService;
    @Mock
    private InternalOrderProductRepository internalOrderProductRepository;
    @Mock
    private ProductRepository productRepository;
    @Spy
    private InternalOrderProductMapper internalOrderProductMapper;

    @Test
    void getAll() {
        when(internalOrderProductRepository.findAll())
                .thenReturn(
                        List.of(
                                InternalOrderProductModelStubs.getInternalOrderProduct(1L),
                                InternalOrderProductModelStubs.getInternalOrderProduct(2L),
                                InternalOrderProductModelStubs.getInternalOrderProduct(3L)
                        ));

        List<InternalOrderProductsDto> internalOrderProductsDtos = internalOrderProductService.getAll();

        assertEquals(3, internalOrderProductsDtos.size());
    }

    @Test
    void getById() {
        when(internalOrderProductRepository.getOne(anyLong()))
                .thenReturn(InternalOrderProductModelStubs.getInternalOrderProduct(1L));

        InternalOrderProductsDto internalOrderProductsDto = internalOrderProductService.getById(1L);

        assertEquals(1, internalOrderProductsDto.getId());
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
        internalOrderProductService.deleteById(anyLong());
        verify(internalOrderProductRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(internalOrderProductRepository.save(any(InternalOrderProduct.class)))
                .thenReturn(InternalOrderProductModelStubs.getInternalOrderProduct(1L));

        InternalOrderProductsDto internalOrderProductsDto = internalOrderProductService
                .create(InternalOrderProductDtoStubs.getDto(1L));

        assertEquals(1, internalOrderProductsDto.getId());
        verify(internalOrderProductRepository).save(any(InternalOrderProduct.class));
    }
}