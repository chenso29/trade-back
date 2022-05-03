package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.Stubs.model.finance.LossModelStubs;
import com.trade_accounting.models.dto.finance.LossDto;
import com.trade_accounting.utils.mapper.finance.LossMapper;
import org.mapstruct.factory.Mappers;

public class LossDtoStubs {
    private static final LossMapper mapper = Mappers.getMapper(LossMapper.class);

    public static LossDto getDto(Long id) {
        return mapper.toDto(LossModelStubs.getLoss(id));
    }
}


