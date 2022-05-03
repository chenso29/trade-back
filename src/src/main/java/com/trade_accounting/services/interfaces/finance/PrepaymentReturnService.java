package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import com.trade_accounting.models.dto.finance.PrepaymentReturnDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface PrepaymentReturnService extends AbstractService<PrepaymentReturnDto>, SearchableService<PrepaymentReturn, PrepaymentReturnDto> {
}
