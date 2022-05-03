package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.models.dto.finance.MutualSettlementsDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.finance.MutualSettlementsMapper;
import org.mapstruct.factory.Mappers;

public class MutualSettlementsDtoStubs {
    private static final MutualSettlementsMapper mapper = Mappers.getMapper(MutualSettlementsMapper.class);
    public static MutualSettlementsDto getMutualSettlementsDto(Long id) {
        return mapper.toDto(ModelStubs.getMutualSettlements(id));
    }
}
