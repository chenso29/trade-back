package src.test.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.ProductionTargets;
import com.trade_accounting.models.dto.production.ProductionTargetsDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.production.ProductionTargetsRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.production.ProductionTargetsDtoStubs;
import com.trade_accounting.utils.mapper.production.ProductionTargetsMapperImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductionTargetsServiceImplTest {

    @InjectMocks
    private ProductionTargetsServiceImpl productionTargetsService;

    @Mock
    private ProductionTargetsRepository productionTargetsRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Spy
    private ProductionTargetsMapperImpl productionTargetsMapper;

    @Test
    void getAll_shouldReturnListFilledProductionTargetsDto() {
        when(productionTargetsRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getProductionTargets(1L),
                                ModelStubs.getProductionTargets(2L),
                                ModelStubs.getProductionTargets(3L)
                        ).collect(Collectors.toList())
                );

        List<ProductionTargetsDto> productionTargets = productionTargetsService.getAll();

        assertNotNull(productionTargets, "Failure - expected that list of productTargets not null");
        assertEquals(3, productionTargets.size(), "failure - expected that size of list of productTargets equals 3");
        verify(productionTargetsRepository).findAll();

        for (ProductionTargetsDto productionTarget : productionTargets) {
            productionTargetsDtoIsCorrectlyInited(productionTarget);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListProductionTargetsDto() {
        when(productionTargetsRepository.findAll())
                .thenReturn(
                        new ArrayList<>()
                );

        List<ProductionTargetsDto> productionTargets = productionTargetsService.getAll();

        assertNotNull(productionTargets, "Failure - expected that list of productTargets not null");
        assertEquals(0, productionTargets.size(), "failure - expected that size of list of list of productTargets equals 0");
    }

    @Test
    void getById_shouldReturnFilledProductionTargetsDto() {
        when(productionTargetsRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getProductionTargets(1L));

        ProductionTargetsDto productionTargetsDto = productionTargetsService.getById(1L);

        productionTargetsDtoIsCorrectlyInited(productionTargetsDto);
    }

    @Test
    void search_shouldReturnListFilledProductionTargetsDto() {
        when(productionTargetsRepository.findAll(Mockito.<Specification<ProductionTargets>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getProductionTargets(1L),
                                ModelStubs.getProductionTargets(2L),
                                ModelStubs.getProductionTargets(3L)
                        ).collect(Collectors.toList())
                );

        List<ProductionTargetsDto> productionTargetsDtos = productionTargetsService
                .search(SpecificationStubs.getProductionTargetsSpecificationStub());

        assertNotNull(productionTargetsDtos, "failure - expected that a list of productionTargetsDtos not null");
        assertTrue(productionTargetsDtos.size() > 0, "failure - expected that a list of productionTargetsDtos greater than 0");

        for (ProductionTargetsDto productionTarget : productionTargetsDtos) {
            productionTargetsDtoIsCorrectlyInited(productionTarget);
        }
    }

    @Test
    void search_shouldReturnEmptyListProductionTargetsDto() {
        when(productionTargetsRepository.findAll(Mockito.<Specification<ProductionTargets>>any()))
                .thenReturn(new ArrayList<>());

        List<ProductionTargetsDto> productionTargetsDtos = productionTargetsService
                .search(SpecificationStubs.getProductionTargetsSpecificationStub());

        assertNotNull(productionTargetsDtos, "failure - expected that a list of productionTargetsDtos not null");
        assertEquals(0, productionTargetsDtos.size(), "failure - expected that size of list of productionTargetsDtos equals 0");
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        productionTargetsService.create(ProductionTargetsDtoStubs.getProductionTargetsDto(1L));

        verify(productionTargetsRepository).save(any(ProductionTargets.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        productionTargetsService.update(ProductionTargetsDtoStubs.getProductionTargetsDto(1L));

        verify(productionTargetsRepository).save(any(ProductionTargets.class));
    }

    @Test
    void deleteById_shouldPassInstructionSuccessfulDelete() {
        productionTargetsService.deleteById(1L);
        verify(productionTargetsRepository).deleteById(anyLong());
    }

    private void productionTargetsDtoIsCorrectlyInited(ProductionTargetsDto productionTargetsDto) {
        assertNotNull(productionTargetsDto, "failure - fail in passed productionTargetsDto");
        assertNotNull(productionTargetsDto.getId(), "failure - fail in field 'id' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getDate(), "failure - fail in field 'date' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getCompanyId(), "failure - fail in field 'companyId' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getDeliveryPlannedMoment(), "failure - fail in field 'deliveryPlannedMoment' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getProductionStart(), "failure - fail in field 'productionStart' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getProductionEnd(), "failure - fail in field 'productionEnd' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getMaterialWarehouse(), "failure - fail in field 'materialWarehouse' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getProductionWarehouse(), "failure - fail in field 'productionWarehouse' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getOwner(), "failure - fail in field 'Owner' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getEmployeeOwner(), "failure - fail in field 'employeeOwner' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getUpdated(), "failure - fail in field 'updated' into productionTargetsDto");
        assertNotNull(productionTargetsDto.getUpdatedByName(), "failure - fail in field 'updatedByName' into productionTargetsDto");
    }
}
