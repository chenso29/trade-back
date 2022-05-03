package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.warehouse.BuyersReturnMapper;
import org.mapstruct.factory.Mappers;

public class BuyersReturnDtoStubs {
    private static final BuyersReturnMapper mapper = Mappers.getMapper(BuyersReturnMapper.class);
    public static BuyersReturnDto getBuyersReturnDto(Long id) {
        return mapper.toDto(ModelStubs.getBuyersReturn(id));
    }
}
