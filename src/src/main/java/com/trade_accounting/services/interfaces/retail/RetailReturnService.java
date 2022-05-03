package src.main.java.com.trade_accounting.services.interfaces.retail;

import com.trade_accounting.models.entity.retail.RetailReturn;
import com.trade_accounting.models.dto.retail.RetailReturnDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface RetailReturnService extends AbstractService<RetailReturnDto>, SearchableService<RetailReturn, RetailReturnDto> {

    List<RetailReturnDto> search(String searchTerm);

}
