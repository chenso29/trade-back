package src.test.java.com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import com.trade_accounting.repositories.invoice.InvoiceProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.Stubs.dto.invoice.InvoiceProductDtoStubs;
import com.trade_accounting.Stubs.model.invoice.InvoiceProductModelStubs;
import com.trade_accounting.utils.mapper.invoice.InvoiceProductMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceProductServiceImplTest {

    @InjectMocks
    private InvoiceProductServiceImpl invoiceProductService;

    @Mock
    private InvoiceProductRepository invoiceProductRepository;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private InvoiceProductMapperImpl invoiceProductMapper;

    @Test
    void getAll_shouldReturnListFilledInvoiceProductDto() {
        when(invoiceProductRepository.findAll())
                .thenReturn(
                        Stream.of(
                                InvoiceProductModelStubs.getInvoiceProduct(1L),
                                InvoiceProductModelStubs.getInvoiceProduct(2L),
                                InvoiceProductModelStubs.getInvoiceProduct(3L)
                        ).collect(Collectors.toList())
                );
        List<InvoiceProductDto> invoiceProductDtoList = invoiceProductService.getAll();
        assertNotNull(
                invoiceProductDtoList,
                "failure - expected that a list of invoiceProductDto not null"
        );
        assertTrue(
                invoiceProductDtoList.size() > 0,
                "failure - expected that a list of invoiceProductDto grater than 0"
        );
        for (InvoiceProductDto invoiceProductDto : invoiceProductDtoList) {
            invoiceProductListDtoIsCorrectlyInited(invoiceProductDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListInvoiceProductDtoList() {
        when(invoiceProductRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<InvoiceProductDto> invoiceDtoList =
                invoiceProductService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceProductDto not null"
        );
        assertEquals(
                0, invoiceDtoList.size(),
                "failure - expected that size of list of invoiceProductDto equals 0"
        );
    }

    @Test
    void getById_shouldReturnFilledInvoiceProductDto() {
        Optional<InvoiceProduct> invoiceProductDtoFromRepo =
                Optional.of(
                        InvoiceProductModelStubs.getInvoiceProduct(1L)
                );

        when(invoiceProductRepository.findById(anyLong()))
                .thenReturn(invoiceProductDtoFromRepo);

        InvoiceProductDto invoiceProductDto =
                invoiceProductService.getById(1L);

        assertNotNull(
                invoiceProductDto,
                "failure - expected that invoiceDto not null"
        );

        invoiceProductListDtoIsCorrectlyInited(invoiceProductDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        invoiceProductService.create(
                InvoiceProductDtoStubs.getInvoiceProductDto(1L)
        );

        verify(invoiceProductRepository).save(any(InvoiceProduct.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        invoiceProductService.update(
                InvoiceProductDtoStubs.getInvoiceProductDto(1L)
        );
        verify(invoiceProductRepository).save(any(InvoiceProduct.class));
    }

    @Test
    void deleteById() {
        invoiceProductService.deleteById(1L);
        verify(invoiceProductRepository).deleteById(1L);
    }

    void invoiceProductListDtoIsCorrectlyInited(InvoiceProductDto invoiceProductDto) {
        assertNotNull(invoiceProductDto, "Fail in passed invoiceDto");
        assertNotNull(invoiceProductDto.getId(), "Fail in field 'id' of invoiceDto");
    }
}
