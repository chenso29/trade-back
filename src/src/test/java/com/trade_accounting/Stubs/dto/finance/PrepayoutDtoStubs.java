package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.Stubs.model.finance.PrepayoutModelStubs;
import com.trade_accounting.models.dto.finance.PrepayoutDto;
import com.trade_accounting.utils.mapper.finance.PrepayoutMapper;
import org.mapstruct.factory.Mappers;

public class PrepayoutDtoStubs {
    private static final PrepayoutMapper mapper = Mappers.getMapper(PrepayoutMapper.class);

    public static PrepayoutDto getDto(Long id) {
        return mapper.toDto(PrepayoutModelStubs.getPrepayout(id));
    }
}
