package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.MutualSettlements;
import com.trade_accounting.models.dto.finance.MutualSettlementsDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface MutualSettlementsService extends AbstractService<MutualSettlementsDto>, SearchableService<MutualSettlements, MutualSettlementsDto> {

}
