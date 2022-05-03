package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.ReturnFromBuyers;
import com.trade_accounting.models.dto.finance.ReturnFromBuyersDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface ReturnFromBuyersService extends AbstractService<ReturnFromBuyersDto>,
        SearchableService<ReturnFromBuyers, ReturnFromBuyersDto> {

}

