package src.test.java.com.trade_accounting.Stubs.dto.purchases;

import com.trade_accounting.Stubs.model.purchases.PurchaseControlModelStubs;
import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.utils.mapper.purchases.PurchaseControlMapper;
import org.mapstruct.factory.Mappers;

public class PurchaseControlDtoStubs {
    private static final PurchaseControlMapper MAPPER = Mappers.getMapper(PurchaseControlMapper.class);

    public static PurchaseControlDto getDto(Long id) {
        return MAPPER.toDto(PurchaseControlModelStubs.getPurchaseControl(id));
    }
}
