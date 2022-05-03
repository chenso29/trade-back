package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.ProductGroup;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import com.trade_accounting.repositories.warehouse.ProductGroupRepository;
import com.trade_accounting.services.impl.warehouse.ProductGroupServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductGroupServiceImplTest {

    @Mock
    private ProductGroupRepository productGroupRepository;

    @InjectMocks
    private ProductGroupServiceImpl productGroupService;

    //Tests
    @Test
    void getAll_shouldReturnListFilledProductDto() {
        when(productGroupRepository.findAll())
                .thenReturn(getListProductGroup());

        List<ProductGroupDto> productGroupDtoList = productGroupService.getAll();

        assertNotNull(productGroupDtoList, "failure - expected that a list of productDto not null: " + productGroupDtoList);
        assertTrue(productGroupDtoList.size() > 0, "failure - expected that a size of list of productDto greater than 0: " + productGroupDtoList);

        for (ProductGroupDto productGroupDto : productGroupDtoList) {
            productGroupDtoIsCorrectlyInited(productGroupDto);
        }
    }

    @Test
    void getById_shouldReturnFilledEmployeeDto() {
        Optional<ProductGroup> productGroupFromRepo = Optional.of(getProductGroup(1L));

        when(productGroupRepository.findById(anyLong()))
                .thenReturn(productGroupFromRepo);

        ProductGroupDto productGroup = productGroupService.getById(1L);

        assertNotNull(productGroup, "failure - expected that employee not null.");

        productGroupDtoIsCorrectlyInited(productGroup);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        ProductGroupDto productGroup = productGroupService.create(
                getProductGroupDto(1L)
        );

        verify(productGroupRepository).save(any(ProductGroup.class));
        verify(productGroupRepository).findById(anyLong());

        productGroupDtoIsCorrectlyInited(productGroup);
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        ProductGroupDto productGroup = productGroupService.update(
                getProductGroupDto(1L)
        );

        verify(productGroupRepository).save(any(ProductGroup.class));
        verify(productGroupRepository).findById(anyLong());

        productGroupDtoIsCorrectlyInited(productGroup);
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        productGroupService.deleteById(1L);
        verify(productGroupRepository).deleteById(1L);
    }

    void productGroupDtoIsCorrectlyInited(ProductGroupDto productGroupDto) {
        assertNotNull(productGroupDto, "Fail in passed employee");
        assertNotNull(productGroupDto.getId(), "Fail in field 'id' of employee");
    }


    //Util methods
    EmployeeDto getEmployeeDtoFromRepo(Long id) {
        return new EmployeeDto(id,
                "LastName",
                "FirstName",
                "MiddleName",
                String.valueOf(id),
                String.valueOf(id).repeat(11),
                String.valueOf(id).repeat(12),
                "Description",
                "email@email.com",
                "password",
                1L,
                1L,
                Set.of(1L),
                1L
                );
    }

    ProductGroupDto getProductGroupDto(Long id) {
        return new ProductGroupDto(
                id, "product group dto",
                "00001", id + 1
        );
    }

    ProductGroup getProductGroup(Long id) {
        if (id == 0) return null;

        ProductGroup parentProductGroup = getProductGroup(id - 1);

        return new ProductGroup(id, "Product group", "00001", parentProductGroup);
    }

    List<ProductGroup> getListProductGroup() {
        return Stream.of(
                getProductGroup(1L),
                getProductGroup(2L),
                getProductGroup(3L)
        ).collect(Collectors.toList());
    }
}