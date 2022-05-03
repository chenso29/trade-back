package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.Payout;
import com.trade_accounting.models.dto.finance.PayoutDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface PayoutService extends AbstractService<PayoutDto>, SearchableService<Payout, PayoutDto> {

    List<PayoutDto> getAllByParametrs(String searchTerm);

}