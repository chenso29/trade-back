package src.test.java.com.trade_accounting.services.impl.units;

import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import com.trade_accounting.repositories.units.UnitRepository;
import com.trade_accounting.services.impl.units.UnitServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnitServiceImplTest {

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private UnitServiceImpl unitService;

    //Tests
    @Test
    void getAll_shouldReturnListFilledUnitDto() {
        when(unitRepository.findAll())
                .thenReturn(getListOfUnitFromRepo());

        List<UnitDto> units = unitService.getAll();

        assertNotNull(units, "Failure - expected that list of units not null");
        assertTrue(units.size() > 0, "Failure - expected that size of list of units greater than 0");

        for (UnitDto unit : units) {
            unitDtoIsCorrectlyInited(unit);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListUnitDto() {
        when(unitRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<UnitDto> units = unitService.getAll();


        assertNotNull(units, "Failure - expected that list of units not null");
        assertEquals(0, units.size(), "Failure - expected that size of list of units equals 0");
    }

    @Test
    void getById_shouldReturnFilledUnitDto() {
        Optional<Unit> unitFromRepo = Optional.of(getUnitFromRepo(1L));

        when(unitRepository.findById(1L))
                .thenReturn(unitFromRepo);

        UnitDto unit = unitService.getById(1L);

        assertNotNull(unit, "failure - expected that unit not null.");
        unitDtoIsCorrectlyInited(unit);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        unitService.create(
                getUnitDto(1L)
        );

        verify(unitRepository).save(any(Unit.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        unitService.update(
                getUnitDto(1L)
        );

        verify(unitRepository).save(any(Unit.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        unitService.deleteById(1L);
        verify(unitRepository).deleteById(1L);
    }

    void unitDtoIsCorrectlyInited(UnitDto unit) {
        assertNotNull(unit, "Fail in passed unit");
        assertNotNull(unit.getId(), "Fail in field 'id' of unit");
    }

    //Util methods
    Unit getUnitFromRepo(Long id) {
        return new Unit(id, "short name", "full name", "0001");
    }

    List<Unit> getListOfUnitFromRepo() {
        return Stream.of(
                getUnitFromRepo(1L),
                getUnitFromRepo(2L),
                getUnitFromRepo(3L)
        ).collect(Collectors.toList());
    }

    UnitDto getUnitDto(Long id) {
        return new UnitDto(id, "short name", "full name", "0001");
    }
}