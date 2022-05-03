package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Shipment;
import com.trade_accounting.models.dto.warehouse.ShipmentDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.warehouse.ShipmentProductRepository;
import com.trade_accounting.repositories.warehouse.ShipmentRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.Stubs.dto.warehouse.ShipmentDtoStubs;
import com.trade_accounting.Stubs.model.warehouse.ShipmentModelStubs;
import com.trade_accounting.utils.mapper.warehouse.ShipmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceImplTest {
    @Mock
    ShipmentRepository shipmentRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Spy
    ShipmentMapper shipmentMapper;

    @Mock
    ShipmentProductRepository shipmentProductRepository;

    @InjectMocks
    ShipmentServiceImpl shipmentService;

    @Test
    void search() {
    }

    @Test
    void getAll() {
        when( shipmentRepository.findAll()).thenReturn(
                List.of(ShipmentModelStubs.getShipment(1L),
                        ShipmentModelStubs.getShipment(2L),
                        ShipmentModelStubs.getShipment(3L)));

        List<ShipmentDto> shipmentDtos = shipmentService.getAll();
        assertNotNull(shipmentDtos, "failure - expected that a list of ShipmenDtos not null");
        assertEquals(3, shipmentDtos.size(), "failure - expected that a list of ShipmentDtos grater than 0");
//        ShipmentDto shipmentDto = shipmentService.getById(1L);







    }

    @Test
    void getById() {


        Optional<Shipment> shipmentDtoFromRepo =
                Optional.of(
                        ShipmentModelStubs.getShipment(1L)
                );
        when(shipmentRepository.findById(anyLong()))
                .thenReturn(shipmentDtoFromRepo);

//        when(shipmentRepository.getOne(anyLong()))
//                .thenReturn(ShipmentModelStubs.getShipment(1L));

        ShipmentDto shipmentDto = shipmentService.getById(1L);

        ShipmentDtoIsCorrectlyInited(shipmentDto);

    }

    @Test
    void create() {saveOrUpdate();
    }

    @Test
    void update() {saveOrUpdate();
    }

    @Test
    void deleteById() {
        shipmentService.deleteById(anyLong());
        verify(shipmentRepository).deleteById(anyLong());
    }

    @Test
    void moveToRecyclebin() {
    }

    @Test
    void restoreFromRecyclebin() {
    }

    private void saveOrUpdate() {
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(ShipmentModelStubs.getShipment(1L));
        ShipmentDto shipmentDto = shipmentService.create(ShipmentDtoStubs.getShipmentDro(1L));
        assertEquals(1,shipmentDto.getId());
        verify(shipmentRepository).save(any(Shipment.class));
    }


    private void ShipmentDtoIsCorrectlyInited(ShipmentDto shipmentDto) {
        assertNotNull(shipmentDto, "failure - fail in passed shipmentDto");
        assertNotNull(shipmentDto.getId(), "failure - fail in field 'id' into shipmentDto");




    }
}