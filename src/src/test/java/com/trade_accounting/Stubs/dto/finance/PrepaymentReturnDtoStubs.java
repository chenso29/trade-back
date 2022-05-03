package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.Stubs.model.finance.PrepaymentReturnModelStubs;
import com.trade_accounting.models.dto.finance.PrepaymentReturnDto;
import com.trade_accounting.utils.mapper.finance.PrepaymentReturnMapper;
import org.mapstruct.factory.Mappers;

public class PrepaymentReturnDtoStubs {

    private static final PrepaymentReturnMapper mapper = Mappers.getMapper(PrepaymentReturnMapper.class);

    public static PrepaymentReturnDto getDto(Long id) {
        return mapper.toDto(PrepaymentReturnModelStubs.getPrepaymentReturn(id));
    }
}
