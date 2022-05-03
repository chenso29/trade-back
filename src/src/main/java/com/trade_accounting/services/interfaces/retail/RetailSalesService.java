package src.main.java.com.trade_accounting.services.interfaces.retail;

import com.trade_accounting.models.entity.retail.RetailSales;
import com.trade_accounting.models.dto.retail.RetailSalesDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface RetailSalesService extends AbstractService<RetailSalesDto>, SearchableService<RetailSales, RetailSalesDto> {

    List<RetailSalesDto> search(String searchTerm);

}
