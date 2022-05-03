package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.impl.warehouse.WarehouseServiceImpl;
import com.trade_accounting.utils.mapper.warehouse.WarehouseMapper;
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
class WarehouseServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @Spy
    private WarehouseMapper warehouseMapper;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @Test
    void getAll_shouldReturnListFilledWarehouseDto() {
        when(warehouseRepository.findAll())
                .thenReturn(getListWarehouseFromRepo());

        List<WarehouseDto> warehouses = warehouseService.getAll();

        assertNotNull(warehouses, "failure - expected that a list of warehouseDto not null");
        assertTrue(warehouses.size() > 0, "failure - expected that a list of warehouseDto grater than 0");

        for (WarehouseDto warehouse : warehouses) {
            warehouseDtoIsCorrectlyInited(warehouse);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListWarehouseDto() {
        when(warehouseRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<WarehouseDto> warehouses = warehouseService.getAll();

        assertNotNull(warehouses, "failure - expected that a list of warehouseDto not null");
        assertEquals(0, warehouses.size(), "failure - expected that size of list of warehouseDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledWarehouseDto() {
        Optional<Warehouse> warehouseFromRepo = Optional.of(getWarehouseFromRepo(1L));

        when(warehouseRepository.findById(anyLong()))
                .thenReturn(warehouseFromRepo);

        WarehouseDto warehouse = warehouseService.getById(1L);

        assertNotNull(warehouse, "failure - expected that warehouse not null");
        warehouseDtoIsCorrectlyInited(warehouse);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        warehouseService.create(
                getWarehouseDto(1L)
        );

        verify(warehouseRepository).save(any(Warehouse.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        warehouseService.update(
                getWarehouseDto(1L)
        );

        verify(warehouseRepository).save(any(Warehouse.class));
    }

    @Test
    void deleteById() {
        warehouseService.deleteById(1L);
        verify(warehouseRepository).deleteById(1L);
    }

    void warehouseDtoIsCorrectlyInited(WarehouseDto warehouse) {
        assertNotNull(warehouse, "Fail in passed employee");
        assertNotNull(warehouse.getId(), "Fail in field 'id' of warehouse");
        assertNotNull(warehouse.getName(), "Fail in field 'name' of warehouse");
    }

    Warehouse getWarehouseFromRepo(Long id) {
        return new Warehouse(
                id, "warehouse",
                "00001", "address",
                "commentToAddress", "comment"
        );
    }

    List<Warehouse> getListWarehouseFromRepo() {
        return Stream.of(
                getWarehouseFromRepo(1L),
                getWarehouseFromRepo(2L),
                getWarehouseFromRepo(3L)
        ).collect(Collectors.toList());
    }

    WarehouseDto getWarehouseDto(Long id) {
        return new WarehouseDto(
                id, "warehouse",
                "0001", "address",
                "commentToAddress", "comment"
        );
    }
}