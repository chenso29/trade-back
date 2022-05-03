package src.test.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.StreetDto;
import com.trade_accounting.models.entity.fias.Street;
import com.trade_accounting.repositories.fias.StreetRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.fias.StreetDtoStubs;
import com.trade_accounting.utils.mapper.fias.StreetMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StreetServiceTest {

    @Mock
    StreetRepository repository;

    @Spy
    StreetMapper streetMapper;

    @InjectMocks
    StreetServiceImpl service;

    @Test
    public void getAllTest() {
        when(repository.findAll()).thenReturn(
                Stream.of(
                        ModelStubs.getStreet(1L),
                        ModelStubs.getStreet(2L),
                        ModelStubs.getStreet(3L)
                ).collect(Collectors.toList())
        );
        List<StreetDto> streetList = service.getAll();
        assertNotNull(streetList, "failure - expected that streetList is not null");
        assertEquals(3, streetList.size(), "failure - expected that size is 3");
        assertTrue(streetList.size() > 0, "failure - expected that a size of list of StreetDto greater than 0");

        for (StreetDto item : streetList) {
            streetDtoIsCorrectlyInited(item);
        }
    }

    @Test
    public void getByIdTest() {
        Optional<Street> streetFromRepo = Optional.of(ModelStubs.getStreet(1L));
        when(repository.findById(anyLong())).thenReturn(streetFromRepo);
        StreetDto result = service.getById(1L);
        assertNotNull(result, "failure - expected that a streetDto not null");
        streetDtoIsCorrectlyInited(result);
    }

    @Test
    public void createTest() {
        when(repository.save(any())).thenReturn(ModelStubs.getStreet(1L));
        StreetDto result = service.create(StreetDtoStubs.getStreetDto(1L));
        verify(repository).save(any(Street.class));
        assertNotNull(result, "failure - expected that a StreetDto not null");
        assertEquals(result, StreetDtoStubs.getStreetDto(1L));
        streetDtoIsCorrectlyInited(result);
    }

    @Test
    public void updateTest() {
        when(repository.save(any())).thenReturn(ModelStubs.getStreet(1L));
        StreetDto result = service.update(StreetDtoStubs.getStreetDto(1L));
        verify(repository).save(any(Street.class));
        assertNotNull(result, "failure - expected that a StreetDto not null");
        assertEquals(result, StreetDtoStubs.getStreetDto(1L));
        streetDtoIsCorrectlyInited(result);
    }

    @Test
    public void deleteByIdTest() {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }

    private void streetDtoIsCorrectlyInited(StreetDto streetDto) {
        assertNotNull(streetDto, "failure - fail in passed streetDto");
        assertNotNull(streetDto.getId(), "failure - fail in field 'id' into streetDto");
        assertNotNull(streetDto.getName(), "failure - fail in field 'name' into streetDto");
        assertNotNull(streetDto.getCityDto(), "failure - fail in field 'CityDto' into streetDto");
    }
}
