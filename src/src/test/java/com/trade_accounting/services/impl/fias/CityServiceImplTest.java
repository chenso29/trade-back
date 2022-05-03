package src.test.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.CityDto;
import com.trade_accounting.models.entity.fias.City;
import com.trade_accounting.repositories.fias.CityRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.fias.CityDtoStubs;
import com.trade_accounting.utils.mapper.fias.CityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceImplTest {

    @Mock
    CityRepository cityRepository;

    @Spy
    CityMapper cityMapper;

    @InjectMocks
    CityServiceImpl cityService;

    @Test
    public void testGetAll_shouldReturnFillListOfCities() {
        when(cityRepository.findAll())
                .thenReturn(Stream.of(
                        ModelStubs.getCity(1L),
                        ModelStubs.getCity(2L),
                        ModelStubs.getCity(3L)
                ).collect(Collectors.toList()));
        List<CityDto> cityList = cityService.getAll();
        assertNotNull(cityList, "failure - expected that cityList is not null");
        assertEquals(3, cityList.size(), "failure - expected that size is 3");
        assertTrue(cityList.size() > 0, " failure - expected that a size of list of CityDto greater than 0");
        for (CityDto cityDto : cityList) {
            cityDtoIsCorrectlyInited(cityDto);
        }
    }

    @Test
    public void getAll_shouldReturnEmptyListCitiesDto() {
        when(cityRepository.findAll())
                .thenReturn(new ArrayList<>());
        List<CityDto> listCities = cityService.getAll();
        assertNotNull(listCities, "failure - expected that a list of cityDto not null");
        assertEquals(0, listCities.size(), "failure - expected that size of list of cityDto equals 0");
    }

    @Test
    public void getById_shouldReturnFilledCityDto() {
        Optional<City> cityFromRepo = Optional.of(ModelStubs.getCity(1L));
        when(cityRepository.findById(anyLong())).thenReturn(cityFromRepo);
        CityDto cityDto = cityService.getById(1L);
        assertNotNull(cityDto, "failure - expected that a cityDto not null");
        cityDtoIsCorrectlyInited(cityDto);
    }

    @Test
    public void updateTest() {
        when(cityRepository.save(any())).thenReturn(ModelStubs.getCity(1L));
        CityDto result = cityService.update(CityDtoStubs.getCityDto(1L));
        verify(cityRepository).save(any(City.class));
        assertNotNull(result, "failure - expected that a districtDto not null");
        assertEquals(result, CityDtoStubs.getCityDto(1L));
    }

    @Test
    public void createTest() {
        when(cityRepository.save(any())).thenReturn(ModelStubs.getCity(1L));
        CityDto result = cityService.create(CityDtoStubs.getCityDto(1L));
        verify(cityRepository).save(any(City.class));
        assertNotNull(result, "failure - expected that a districtDto not null");
    }

    @Test
    public void deleteByIdTest() {
        cityService.deleteById(1L);
        verify(cityRepository).deleteById(anyLong());
    }

    private void cityDtoIsCorrectlyInited(CityDto cityDto) {
        assertNotNull(cityDto, "failure - fail in passed cityDto");
        assertNotNull(cityDto.getId(), "failure - fail in field 'id' into cityDto");
        assertNotNull(cityDto.getName(), "failure - fail in field 'name' into cityDto");
        assertNotNull(cityDto.getDistrictDto(), "failure - fail in field 'DistrictDto' into cityDto");
        assertNotNull(cityDto.getStreetsDto(), "failure - fail in field 'StreetDto' into cityDto");
    }
}
