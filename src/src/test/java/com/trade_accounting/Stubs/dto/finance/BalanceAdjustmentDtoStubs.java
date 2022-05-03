package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.models.dto.finance.BalanceAdjustmentDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.finance.BalanceAdjustmentMapper;
import org.mapstruct.factory.Mappers;

public class BalanceAdjustmentDtoStubs {
    private static final BalanceAdjustmentMapper mapper = Mappers.getMapper(BalanceAdjustmentMapper.class);

    public static BalanceAdjustmentDto getBalanceAdjustmentDto(Long id) {
        return mapper.toDto(ModelStubs.getBalanceAdjustment(id));
    }
}
