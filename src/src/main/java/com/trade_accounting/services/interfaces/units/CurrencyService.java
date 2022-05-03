package src.main.java.com.trade_accounting.services.interfaces.units;

import com.trade_accounting.models.entity.units.Currency;
import com.trade_accounting.models.dto.units.CurrencyDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface CurrencyService extends AbstractService<CurrencyDto>, SearchableService<Currency, CurrencyDto> {

    List<CurrencyDto> searchByString(String text);

}
