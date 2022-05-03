package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.Correction;
import com.trade_accounting.models.dto.finance.CorrectionDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface CorrectionService extends AbstractService<CorrectionDto>, SearchableService<Correction, CorrectionDto> {

    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);

}

