package src.test.java.com.trade_accounting.Stubs.dto.purchases;

import com.trade_accounting.Stubs.model.purchases.PurchaseForecastStubs;
import com.trade_accounting.models.dto.purchases.PurchaseForecastDto;
import com.trade_accounting.utils.mapper.purchases.PurchaseForecastMapper;
import org.mapstruct.factory.Mappers;

public class PurchaseForecastDtoStubs {
    private static final PurchaseForecastMapper MAPPER = Mappers.getMapper(PurchaseForecastMapper.class);

    public static PurchaseForecastDto getDto(Long id) {
        return MAPPER.toDto(PurchaseForecastStubs.getPurchaseForecast(id));
    }
}
