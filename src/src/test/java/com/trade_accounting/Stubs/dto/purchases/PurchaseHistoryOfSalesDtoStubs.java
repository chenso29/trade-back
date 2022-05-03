package src.test.java.com.trade_accounting.Stubs.dto.purchases;

import com.trade_accounting.Stubs.model.purchases.PurchaseHistoryOfSalesStubs;
import com.trade_accounting.models.dto.purchases.PurchaseHistoryOfSalesDto;
import com.trade_accounting.utils.mapper.purchases.PurchaseHistoryOfSalesMapper;
import org.mapstruct.factory.Mappers;

public class PurchaseHistoryOfSalesDtoStubs {
    private static final PurchaseHistoryOfSalesMapper mapper = Mappers.getMapper(PurchaseHistoryOfSalesMapper.class);

    public static PurchaseHistoryOfSalesDto getDto(Long id) {
        return mapper.toDto(PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(id));
    }
}
