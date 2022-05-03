package src.main.java.com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface WarehouseService extends AbstractService<WarehouseDto>, SearchableService<Warehouse, WarehouseDto> {

    List<WarehouseDto> searchByString(String text);

}
