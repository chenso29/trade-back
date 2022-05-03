package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.CorrectionProduct;
import com.trade_accounting.models.dto.finance.CorrectionProductDto;
import com.trade_accounting.repositories.finance.CorrectionProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.finance.CorrectionProductDtoStubs;
import com.trade_accounting.utils.mapper.finance.CorrectionProductMapper;
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
class CorrectionProductServiceImplTest {

    @Mock
    CorrectionProductRepository correctionProductRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    CorrectionProductMapper correctionProductMapper;

    @InjectMocks
    CorrectionProductServiceImpl correctionProductService;

    @Test
    void getAll_shouldReturnFilledListCorrectionProduct() {
        when(correctionProductRepository.findAll())
                .thenReturn(List.of(ModelStubs.getCorrectionProduct(1L),
                        ModelStubs.getCorrectionProduct(2L),
                        ModelStubs.getCorrectionProduct(3L)));

        List<CorrectionProductDto> correctionProductDtos = correctionProductService.getAll();
        assertEquals(3, correctionProductDtos.size());
    }

    @Test
    void getById_shouldReturnFilledCorrectionProduct() {
        Optional<CorrectionProduct> correctionProduct = Optional.of(ModelStubs.getCorrectionProduct(1L));

        when(correctionProductRepository.findById(anyLong())).thenReturn(correctionProduct);

        CorrectionProductDto correctionProductDto = correctionProductService.getById(1L);
        assertEquals(1L, correctionProductDto.getId());
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
        correctionProductService.deleteById(anyLong());
        verify(correctionProductRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(correctionProductRepository.save(any())).thenReturn(ModelStubs.getCorrectionProduct(1L));
        CorrectionProductDto correctionProductDto = correctionProductService.create(CorrectionProductDtoStubs.getCorrectionProductDto(1L));
        assertEquals(1, correctionProductDto.getId());
        verify(correctionProductRepository).save(any(CorrectionProduct.class));
    }
}