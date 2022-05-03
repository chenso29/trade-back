package src.test.java.com.trade_accounting.Stubs.dto.fias;

import com.trade_accounting.models.dto.fias.CityDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.fias.CityMapper;
import org.mapstruct.factory.Mappers;

public class CityDtoStubs {
    private static final CityMapper mapper = Mappers.getMapper(CityMapper.class);

    public static CityDto getCityDto(Long id) {
        return mapper.toDto(ModelStubs.getCity(id));
    }
}
