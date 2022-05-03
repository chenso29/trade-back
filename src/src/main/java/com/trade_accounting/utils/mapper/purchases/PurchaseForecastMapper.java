package src.main.java.com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import com.trade_accounting.models.dto.purchases.PurchaseForecastDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseForecastMapper {

    default PurchaseForecast toModel(PurchaseForecastDto purchaseForecastDto) {
        if (purchaseForecastDto == null) {
            return null;
        }

        return PurchaseForecast.builder()
                .id(purchaseForecastDto.getId())
                .reservedDays(purchaseForecastDto.getReservedDays())
                .reservedProducts(purchaseForecastDto.getReservedProducts())
                .ordered(purchaseForecastDto.getOrdered())
                .build();
    }

    default PurchaseForecastDto toDto(PurchaseForecast purchaseForecast) {
        PurchaseForecastDto purchaseForecastDto = new PurchaseForecastDto();
        if(purchaseForecast == null) {
            return null;
        }

        purchaseForecastDto.setId(purchaseForecast.getId());
        purchaseForecastDto.setReservedDays(purchaseForecast.getReservedDays());
        purchaseForecastDto.setReservedProducts(purchaseForecast.getReservedProducts());
        purchaseForecastDto.setOrdered(purchaseForecast.getOrdered());
        return purchaseForecastDto;
    }
}
