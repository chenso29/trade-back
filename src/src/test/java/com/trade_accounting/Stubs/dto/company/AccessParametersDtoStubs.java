package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.company.AccessParametersDto;
import com.trade_accounting.utils.mapper.company.AccessParametersMapper;
import org.mapstruct.factory.Mappers;

public class AccessParametersDtoStubs {
    private static final AccessParametersMapper mapper = Mappers.getMapper(AccessParametersMapper.class);
    public static AccessParametersDto getAccessParametersDto(Long id) {
        return mapper.toDto(ModelStubs.getAccessParameters(id));
    }
}
