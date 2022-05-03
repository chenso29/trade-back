package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Movement;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.warehouse.MovementProductRepository;
import com.trade_accounting.repositories.warehouse.MovementRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.Stubs.dto.warehouse.MovementDtoStubs;
import com.trade_accounting.Stubs.model.warehouse.MovementModelStubs;
import com.trade_accounting.utils.mapper.warehouse.MovementMapperImpl;
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
public class MovementServiceImplTest {

    @Mock
    MovementRepository movementRepository;

    @Mock
    MovementProductRepository movementProductRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Mock
    CompanyRepository companyRepository;

    @Spy
    MovementMapperImpl movementMapper;

    @InjectMocks
    MovementServiceImpl movementService;

    @Test
    void getAll_shouldReturnFilledListMovement() {
        when(movementRepository.getAll()).thenReturn(
                List.of(MovementModelStubs.getMovement(1L),
                        MovementModelStubs.getMovement(2L),
                        MovementModelStubs.getMovement(3L))
        );

        List<MovementDto> movementDtos = movementService.getAll();

        assertEquals(3, movementDtos.size());
    }

    @Test
    void getById_shouldReturnFilledMovement() {
        when(movementRepository.getMovementById(anyLong())).thenReturn(MovementModelStubs.getMovement(1L));

        MovementDto movementDto = movementService.getById(1L);

        assertEquals(1, movementDto.getId());
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
        movementService.deleteById(anyLong());
        verify(movementRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(movementRepository.save(any(Movement.class))).thenReturn(MovementModelStubs.getMovement(1L));
        MovementDto movementDto = movementService.create(MovementDtoStubs.getMovementDto(1L));
        assertEquals(1, movementDto.getId());
        verify(movementRepository).save(any(Movement.class));
    }
}
