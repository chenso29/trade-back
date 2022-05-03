package src.test.java.com.trade_accounting.Stubs.dto.fias;

import com.trade_accounting.models.dto.fias.DistrictDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.fias.DistrictMapper;
import org.mapstruct.factory.Mappers;

public class DistrictDtoStubs {
    private static final DistrictMapper mapper = Mappers.getMapper(DistrictMapper.class);

    public static DistrictDto getDistrictDto(Long id) {
        return mapper.toDto(ModelStubs.getDistrict(id));
    }
}
