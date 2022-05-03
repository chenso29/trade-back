package src.test.java.com.trade_accounting.Stubs.dto.purchases;

import com.trade_accounting.Stubs.model.purchases.PurchaseCurrentBalanceStubs;
import com.trade_accounting.models.dto.purchases.PurchaseCurrentBalanceDto;
import com.trade_accounting.utils.mapper.purchases.PurchaseCurrentBalanceMapper;
import org.mapstruct.factory.Mappers;

public class PurchaseCurrentBalanceDtoStubs {
    private static final PurchaseCurrentBalanceMapper MAPPER = Mappers.getMapper(PurchaseCurrentBalanceMapper.class);

    public static PurchaseCurrentBalanceDto getDto(Long id) {
        return MAPPER.toDto(PurchaseCurrentBalanceStubs.getPurchaseCurrentBalance(id));
    }
}
