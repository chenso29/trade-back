package src.main.java.com.trade_accounting.services.interfaces.production;

import com.trade_accounting.models.entity.production.OrdersOfProduction;
import com.trade_accounting.models.dto.production.OrdersOfProductionDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface OrdersOfProductionService extends AbstractService<OrdersOfProductionDto>, SearchableService<OrdersOfProduction, OrdersOfProductionDto> {

    List<OrdersOfProductionDto> search(String searchTerm);
}
