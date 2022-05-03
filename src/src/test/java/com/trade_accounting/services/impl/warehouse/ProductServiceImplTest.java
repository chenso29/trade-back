package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.util.ImageDto;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.repositories.util.ImageRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.utils.mapper.util.ImageMapperImpl;
import com.trade_accounting.utils.mapper.warehouse.ProductMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Spy
    private ImageMapperImpl imageMapper;

    @Spy
    private ProductMapperImpl productMapper;


    @Test
    void getById() {
        ProductDto productDto = ModelStubs.getProductDto(1L);
        productDto.setImageDtos(new ArrayList<>());

        Product product = ModelStubs.getProduct(1L);
        product.setImages(new ArrayList<>());

        when(repository.getOne(1L)).thenReturn(product);

        ProductDto fact = productService.getById(1L);

        verify(productMapper).toDto(product);
        verify(imageMapper).toListDto(product.getImages());
        assertEquals(productDto, fact);
    }

    @Test
    void whenGetAllByProductGroupIdThenReturnListOfProducts() {
        when(repository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getProduct(1L),
                                ModelStubs.getProduct(2L),
                                ModelStubs.getProduct(3L)
                        ).collect(Collectors.toList())
                );
        List<ProductDto> productDtos = productService.getAll();

        assertNotNull(productDtos, "failure - expected that a list of productDtos not null");
        assertEquals(productDtos.size(), 3, "failure - expected that a size of list of productDtos equals 3");

    }

    @Test
    void whenGetAllThenShouldReturnEmptyListProductDto() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        List<ProductDto> employees = productService.getAll();

        assertNotNull(employees, "failure - expected that a list of ProductDto not null");
        assertEquals(0, employees.size(), "failure - expected that size of list of ProductDto equals 0");
    }

    @Test
    void whenCreateProduct() {
        List<ImageDto> imageDtoList = Stream.of(
                ModelStubs.getImageDto(1L),
                ModelStubs.getImageDto(2L),
                ModelStubs.getImageDto(3L)
        ).collect(Collectors.toList());
        ProductDto productDto = ModelStubs.getProductDto(1L);
        productDto.setImageDtos(imageDtoList);
        productService.create(productDto);
        verify(imageMapper).toListModel(any(List.class), anyString());
        verify(imageRepository).saveAll(any(List.class));
        verify(productMapper).toModel(productDto);
        verify(repository).saveAndFlush(any(Product.class));
    }

    @Test
    void deleteById() {
        productService.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    //ещё проблемный тест
    @Test
    void getAllByContractorId() {
        List<Product> productList = Stream.of(ModelStubs.getProduct(1L)).collect(Collectors.toList());
        when(repository.getAllByContractorId(1L)).thenReturn(productList);// валится здесь

        List<ProductDto> allByContractorId = productService.getAllByContractorId(1L);

        verify(repository).getAllByContractorId(1L);// здесь
        verify(productMapper).toListDto(any(List.class));
        assertEquals(productMapper.toListDto(productList), allByContractorId);// и здесь
    }

    @Test
    void search() {
        String searchValue = "value";
        List<Product> stubProductList = Stream.of(ModelStubs.getProduct(1L)).collect(Collectors.toList());
        when(repository.search(searchValue)).thenReturn(stubProductList);

        List<ProductDto> expectedCollect = Stream.of(ModelStubs.getProductDto(1L)).collect(Collectors.toList());

        List<ProductDto> factCollect = productService.search(searchValue);
        verify(productMapper).toListDto(stubProductList);
        assertEquals(expectedCollect, factCollect);
    }

    @Test
    void searchByFilter() {
        when(repository.findAll(Mockito.<Specification<Product>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getProduct(1L),
                                ModelStubs.getProduct(2L),
                                ModelStubs.getProduct(3L)
                        ).collect(Collectors.toList())
                );

        List<ProductDto> actualProductDtos = productService
                .search(SpecificationStubs.getProductSpecificationStub());

        assertNotNull(actualProductDtos, "failure - expected that a list of employeeDto not null");
        assertEquals(3, actualProductDtos.size(), "failure - expected that a list of employeeDto equals 3");
    }
}
