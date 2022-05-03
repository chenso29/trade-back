package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.Loss;
import com.trade_accounting.models.dto.finance.LossDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface LossService extends AbstractService<LossDto>, SearchableService<Loss, LossDto> {
    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);
}

