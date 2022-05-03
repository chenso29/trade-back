package src.main.java.com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.entity.units.Currency;
import com.trade_accounting.models.dto.units.CurrencyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    //Currency
    CurrencyDto toDto(Currency currency);

    Currency toModel(CurrencyDto currencyDto);
}
