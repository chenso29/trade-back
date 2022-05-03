package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.Stubs.model.finance.PayoutModelStubs;
import com.trade_accounting.models.dto.finance.PayoutDto;
import com.trade_accounting.utils.mapper.finance.PayoutMapper;
import org.mapstruct.factory.Mappers;

public class PayoutDtoStubs {
    private static final PayoutMapper mapper = Mappers.getMapper(PayoutMapper.class);

    public static PayoutDto getDto(Long id) {
        return mapper.toDto(PayoutModelStubs.getPayout(id));
    }
}
