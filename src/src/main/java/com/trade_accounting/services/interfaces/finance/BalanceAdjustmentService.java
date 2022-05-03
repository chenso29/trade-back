package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.BalanceAdjustment;
import com.trade_accounting.models.dto.finance.BalanceAdjustmentDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface BalanceAdjustmentService extends AbstractService<BalanceAdjustmentDto>,
        SearchableService<BalanceAdjustment, BalanceAdjustmentDto> {

    List<BalanceAdjustmentDto> searchByString(String nameFilter);
}
