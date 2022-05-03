package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.AttributeOfCalculationObject;
import com.trade_accounting.models.dto.warehouse.AttributeOfCalculationObjectDto;
import com.trade_accounting.repositories.warehouse.AttributeOfCalculationObjectRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.warehouse.AttributeOfCalculationObjectDtoStubs;
import com.trade_accounting.utils.mapper.warehouse.AttributeOfCalculationObjectMapper;
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
class AttributeOfCalculationObjectServiceImplTest {

    @Mock
    private AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;

    @Spy
    private AttributeOfCalculationObjectMapper attributeOfCalculationObjectMapper;

    @InjectMocks
    private AttributeOfCalculationObjectServiceImpl attributeOfCalculationObjectService;

    @Test
    void getAll_shouldReturnListFilledAttributeOfCalculationObjectDto() {
        when(attributeOfCalculationObjectRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getAttributeOfCalculationObject(1L),
                                ModelStubs.getAttributeOfCalculationObject(2L),
                                ModelStubs.getAttributeOfCalculationObject(3L)
                        ).collect(Collectors.toList())
                );

        List<AttributeOfCalculationObjectDto> attributeOfCalculationObjects = attributeOfCalculationObjectService.getAll();

        assertNotNull(
                attributeOfCalculationObjects,
                "failure - expected that a list of attributeOfCalculationObjectDto not null"
        );
        assertTrue(
                attributeOfCalculationObjects.size() > 0,
                "failure - expected that a list of attributeOfCalculationObjectDto grater than 0"
        );

        for (AttributeOfCalculationObjectDto attributeOfCalculationObject : attributeOfCalculationObjects) {
            attributeOfCalculationObjectDtoIsCorrectlyInited(attributeOfCalculationObject);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListAttributeOfCalculationObjectDto() {
        when(attributeOfCalculationObjectRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<AttributeOfCalculationObjectDto> attributeOfCalculationObjects =
                attributeOfCalculationObjectService.getAll();

        assertNotNull(
                attributeOfCalculationObjects,
                "failure - expected that a list of attributeOfCalculationObjectDto not null"
        );
        assertEquals(
                0, attributeOfCalculationObjects.size(),
                "failure - expected that size of list of attributeOfCalculationObjectDto equals 0"
        );
    }

    @Test
    void getById_shouldReturnFilledAttributeOfCalculationObjectDto() {
        Optional<AttributeOfCalculationObject> attributeOfCalculationObjectFromRepo =
                Optional.of(
                        ModelStubs.getAttributeOfCalculationObject(1L)
                );

        when(attributeOfCalculationObjectRepository.findById(anyLong()))
                .thenReturn(attributeOfCalculationObjectFromRepo);

        AttributeOfCalculationObjectDto attributeOfCalculationObject =
                attributeOfCalculationObjectService.getById(1L);

        assertNotNull(
                attributeOfCalculationObject,
                "failure - expected that attributeOfCalculationObject not null"
        );

        attributeOfCalculationObjectDtoIsCorrectlyInited(attributeOfCalculationObject);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        attributeOfCalculationObjectService.create(
                AttributeOfCalculationObjectDtoStubs.getAttributeOfCalculationObjectDto(1L)
        );

        verify(attributeOfCalculationObjectRepository).save(any(AttributeOfCalculationObject.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        attributeOfCalculationObjectService.update(
                AttributeOfCalculationObjectDtoStubs.getAttributeOfCalculationObjectDto(1L)
        );

        verify(attributeOfCalculationObjectRepository).save(any(AttributeOfCalculationObject.class));
    }

    @Test
    void deleteById() {
        attributeOfCalculationObjectService.deleteById(1L);
        verify(attributeOfCalculationObjectRepository).deleteById(1L);
    }

    void attributeOfCalculationObjectDtoIsCorrectlyInited(AttributeOfCalculationObjectDto attributeOfCalculationObject) {
        assertNotNull(attributeOfCalculationObject, "Fail in passed employee");
        assertNotNull(attributeOfCalculationObject.getId(), "Fail in field 'id' of attributeOfCalculationObject");
    }
}