package src.main.java.com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.entity.warehouse.Inventarization;
import com.trade_accounting.models.dto.warehouse.InventarizationDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface InventarizationService extends AbstractService<InventarizationDto>, SearchableService<Inventarization, InventarizationDto> {

    List<InventarizationDto> search(String search);

    void moveToRecyclebin(long id);

    void restoreFromRecyclebin(long id);

}
