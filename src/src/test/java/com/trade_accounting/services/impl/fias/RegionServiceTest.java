package src.test.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.RegionDto;
import com.trade_accounting.models.entity.fias.Region;
import com.trade_accounting.repositories.fias.RegionRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.fias.RegionDtoStubs;
import com.trade_accounting.utils.mapper.fias.RegionMapper;
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
public class RegionServiceTest {

    @Mock
    RegionRepository repository;

    @Spy
    RegionMapper regionMapper;

    @InjectMocks
    RegionServiceImpl service;

    @Test
    public void getAllTest() {
        when(repository.findAll()).thenReturn(
                Stream.of(
                        ModelStubs.getRegion(1L),
                        ModelStubs.getRegion(2L),
                        ModelStubs.getRegion(3L)
                ).collect(Collectors.toList())
        );
        List<RegionDto> regionList = service.getAll();
        assertNotNull(regionList, "failure - expected that regionList is not null");
        assertEquals(3, regionList.size(), "failure - expected that size is 3");
        assertTrue(regionList.size() > 0, "failure - expected that a size of list of RegionDto greater than 0");

        for (RegionDto item : regionList) {
            regionDtoIsCorrectlyInited(item);
        }
    }

    @Test
    public void getByIdTest() {
        Optional<Region> regionFromRepo = Optional.of(ModelStubs.getRegion(1L));
        when(repository.findById(anyLong())).thenReturn(regionFromRepo);
        RegionDto regionDto = service.getById(1L);
        assertNotNull(regionDto, "failure - expected that a regionDto not null");
        regionDtoIsCorrectlyInited(regionDto);
    }

    @Test
    public void createTest() {
        when(repository.save(any())).thenReturn(ModelStubs.getRegion(1L));
        RegionDto result = service.create(RegionDtoStubs.getRegionDto(1L));
        verify(repository).save(any(Region.class));
        assertNotNull(result, "failure - expected that a StreetDto not null");
        assertEquals(result, RegionDtoStubs.getRegionDto(1L));
        regionDtoIsCorrectlyInited(result);
    }

    @Test
    public void updateTest() {
        when(repository.save(any())).thenReturn(ModelStubs.getRegion(1L));
        RegionDto result = service.update(RegionDtoStubs.getRegionDto(1L));
        verify(repository).save(any(Region.class));
        assertNotNull(result, "failure - expected that a districtDto not null");
        assertEquals(result, RegionDtoStubs.getRegionDto(1L));
        regionDtoIsCorrectlyInited(result);
    }

    @Test
    public void deleteByIdTest() {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }

    private void regionDtoIsCorrectlyInited(RegionDto regionDto) {
        assertNotNull(regionDto, "failure - fail in passed regionDto");
        assertNotNull(regionDto.getId(), "failure - fail in field 'id' into regionDto");
        assertNotNull(regionDto.getName(), "failure - fail in field 'name' into regionDto");
        assertNotNull(regionDto.getDistrictDtos(), "failure - fail in field 'DistrictDto' into regionDto");
    }
}
