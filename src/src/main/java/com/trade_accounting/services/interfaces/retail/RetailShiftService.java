package src.main.java.com.trade_accounting.services.interfaces.retail;

import com.trade_accounting.models.dto.retail.RetailShiftDto;
import com.trade_accounting.models.entity.retail.RetailShift;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface RetailShiftService extends AbstractService<RetailShiftDto>, SearchableService<RetailShift, RetailShiftDto> {

    List<RetailShiftDto> search(String searchTerm);
}
