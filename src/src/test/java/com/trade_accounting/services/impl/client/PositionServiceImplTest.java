package src.test.java.com.trade_accounting.services.impl.client;

import com.trade_accounting.models.entity.client.Position;
import com.trade_accounting.models.dto.client.PositionDto;
import com.trade_accounting.repositories.client.PositionRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.client.PositionDtoStubs;
import com.trade_accounting.utils.mapper.client.PositionMapperImpl;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PositionServiceImplTest {

    @Mock
    private PositionRepository positionRepository;

    @Spy
    private PositionMapperImpl positionMapper;

    @InjectMocks
    private PositionServiceImpl positionService;

    @Test
    void getAll_shouldReturnListFilledPositionDto() {
        when(positionRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getPosition(1L),
                                ModelStubs.getPosition(2L),
                                ModelStubs.getPosition(3L)
                        )
                                .collect(Collectors.toList())
                );

        List<PositionDto> positions = positionService.getAll();

        assertNotNull(positions, "failure - expected that list of position not null");
        assertTrue(positions.size() > 0, "failure - expected that size of list of position greater than 0");

        for (PositionDto positionDto : positions) {
            positionDtoIsCorrectlyInited(positionDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListPositionDto() {
        when(positionRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<PositionDto> positions = positionService.getAll();

        assertNotNull(positions, "Failure - expected that list of position not null");
        assertEquals(0, positions.size(), "failure - expected that size of list of position equals 0");
    }

    @Test
    void getById_shouldReturnFilledPositionDto() {
        Optional<Position> positionFromRepo = Optional.of(ModelStubs.getPosition(1L));

        when(positionRepository.findById(anyLong()))
                .thenReturn(positionFromRepo);

        PositionDto positionDto = positionService.getById(1L);

        positionDtoIsCorrectlyInited(positionDto);
    }

    @Test
    void getByName_shouldReturnFilledPositionDto() {
        Optional<Position> positionFromRepo = Optional.of(ModelStubs.getPosition(1L));

        when(positionRepository.findByName(anyString()))
                .thenReturn(positionFromRepo);

        PositionDto positionDto = positionService.getByName("name");

        positionDtoIsCorrectlyInited(positionDto);
    }

    @Test
    void getByName_shouldReturnEmptyPositionDto() {
        Optional<Position> positionFromRepo = Optional.empty();

        when(positionRepository.findByName(anyString()))
                .thenReturn(positionFromRepo);

        PositionDto positionDto = positionService.getByName("name");

        assertEquals(new PositionDto(), positionDto, "failure - expected that position was empty");
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        positionService.create(PositionDtoStubs.getPositionDto(1L));

        verify(positionRepository).save(any(Position.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        positionService.update(PositionDtoStubs.getPositionDto(1L));

        verify(positionRepository).save(any(Position.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        positionService.deleteById(1L);

        verify(positionRepository).deleteById(1L);
    }

    private void positionDtoIsCorrectlyInited(PositionDto positionDto) {
        assertNotNull(positionDto, "failure - fail in passed positionDto");
        assertNotNull(positionDto.getId(), "failure - fail in field 'id' into positionDto");
        assertNotNull(positionDto.getName(), "failure - fail in field 'name' into positionDto");
        assertNotNull(positionDto.getSortNumber(), "failure - fail in field 'sortNumber' into positionDto");
    }
}