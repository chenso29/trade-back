package src.test.java.com.trade_accounting.Stubs.dto.fias;

import com.trade_accounting.models.dto.fias.FiasAddressModelDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.fias.FiasAddressModelMapper;
import org.mapstruct.factory.Mappers;

public class FiasAddressModelDtoStubs {
    private static final FiasAddressModelMapper mapper = Mappers.getMapper(FiasAddressModelMapper.class);

    public static FiasAddressModelDto getFiasAddressModelDto(Long id) {
        return mapper.toDto(ModelStubs.getFiasAddressModel(id));
    }
}
