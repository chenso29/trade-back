package src.test.java.com.trade_accounting.Stubs.dto.units;

import com.trade_accounting.models.dto.units.CurrencyDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.units.CurrencyMapper;
import org.mapstruct.factory.Mappers;

public class CurrencyDtoStubs {
    private static final CurrencyMapper mapper = Mappers.getMapper(CurrencyMapper.class);
    public static CurrencyDto getCurrencyDto(Long id) {
        return mapper.toDto(
                ModelStubs.getCurrency(id)
        );
    }
}
