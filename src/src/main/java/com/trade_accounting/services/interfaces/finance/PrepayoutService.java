package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.Prepayout;
import com.trade_accounting.models.dto.finance.PrepayoutDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;


public interface PrepayoutService extends AbstractService<PrepayoutDto>, SearchableService<Prepayout, PrepayoutDto> {
}
