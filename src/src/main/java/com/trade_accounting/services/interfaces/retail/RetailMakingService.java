package src.main.java.com.trade_accounting.services.interfaces.retail;

import com.trade_accounting.models.entity.retail.RetailMaking;
import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface RetailMakingService extends AbstractService<RetailMakingDto>, SearchableService<RetailMaking, RetailMakingDto> {

    List<RetailMakingDto> search(String search);

}
