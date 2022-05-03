package src.test.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.DistrictDto;
import com.trade_accounting.models.entity.fias.District;
import com.trade_accounting.repositories.fias.DistrictRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.fias.DistrictDtoStubs;
import com.trade_accounting.utils.mapper.fias.DistrictMapper;
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
public class DistrictServiceTest {
    @Mock
    DistrictRepository repository;

    @Spy
    DistrictMapper districtMapper;

    @InjectMocks
    DistrictServiceImpl service;

    @Test
    public void getAllTest() {
        when(repository.findAll()).thenReturn(
                Stream.of(
                        ModelStubs.getDistrict(1L),
                        ModelStubs.getDistrict(2L),
                        ModelStubs.getDistrict(3L)
                ).collect(Collectors.toList())
        );
        List<DistrictDto> districtList = service.getAll();
        assertNotNull(districtList, "failure - expected that districtList is not null");
        assertEquals(3, districtList.size(), "failure - expected that size is 3");
        assertTrue(districtList.size() > 0, "failure - expected that a size of list of DistrictDto greater than 0");

        for (DistrictDto item : districtList) {
            districtDtoIsCorrectlyInited(item);
        }
    }

    @Test
    public void getByIdTest() {
        Optional<District> districtFromRepo = Optional.of(ModelStubs.getDistrict(1L));
        when(repository.findById(anyLong())).thenReturn(districtFromRepo);
        DistrictDto districtDto = service.getById(1L);
        assertNotNull(districtDto, "failure - expected that a districtDto not null");
        districtDtoIsCorrectlyInited(districtDto);
    }

    @Test
    public void createTest() {
        when(repository.save(any())).thenReturn(ModelStubs.getDistrict(1L));
        DistrictDto result = service.create(DistrictDtoStubs.getDistrictDto(1L));
        verify(repository).save(any(District.class));
        assertNotNull(result, "failure - expected that a districtDto not null");
        assertEquals(result, DistrictDtoStubs.getDistrictDto(1L));
    }

    @Test
    public void updateTest() {
        when(repository.save(any())).thenReturn(ModelStubs.getDistrict(1L));
        DistrictDto result = service.create(DistrictDtoStubs.getDistrictDto(1L));
        verify(repository).save(any(District.class));
        assertNotNull(result, "failure - expected that a districtDto not null");
        assertEquals(result, DistrictDtoStubs.getDistrictDto(1L));
    }

    @Test
    public void deleteByIdTest() {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }

    private void districtDtoIsCorrectlyInited(DistrictDto districtDto) {
        assertNotNull(districtDto, "failure - fail in passed districtDto");
        assertNotNull(districtDto.getId(), "failure - fail in field 'id' into districtDto");
        assertNotNull(districtDto.getName(), "failure - fail in field 'name' into districtDto");
        assertNotNull(districtDto.getCitiesDto(), "failure - fail in field 'CitiesDto' into districtDto");
        assertNotNull(districtDto.getRegionDto(), "failure - fail in field 'RegionDto' into districtDto");
    }
}
