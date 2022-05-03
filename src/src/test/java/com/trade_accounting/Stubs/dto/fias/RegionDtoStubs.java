package src.test.java.com.trade_accounting.Stubs.dto.fias;

import com.trade_accounting.models.dto.fias.RegionDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.fias.RegionMapper;
import org.mapstruct.factory.Mappers;

public class RegionDtoStubs {
    private static final RegionMapper mapper = Mappers.getMapper(RegionMapper.class);

    public static RegionDto getRegionDto(Long id) {
        return mapper.toDto(ModelStubs.getRegion(id));
    }
}
