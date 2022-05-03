package src.test.java.com.trade_accounting.Stubs.dto.fias;

import com.trade_accounting.models.dto.fias.StreetDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.fias.StreetMapper;
import org.mapstruct.factory.Mappers;

public class StreetDtoStubs {
    private static final StreetMapper mapper = Mappers.getMapper(StreetMapper.class);

    public static StreetDto getStreetDto(Long id) {
        return mapper.toDto(ModelStubs.getStreet(id));
    }
}
