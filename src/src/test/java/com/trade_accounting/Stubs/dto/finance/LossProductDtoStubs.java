package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.Stubs.model.finance.LossProductModelStubs;
import com.trade_accounting.models.dto.finance.LossProductDto;
import com.trade_accounting.utils.mapper.finance.LossProductMapper;
import org.mapstruct.factory.Mappers;

public class LossProductDtoStubs {
    private static final LossProductMapper mapper = Mappers.getMapper(LossProductMapper.class);

    public static LossProductDto getDto(Long id) {
        return mapper.toDto(LossProductModelStubs.getLossProduct(id));
    }
}
