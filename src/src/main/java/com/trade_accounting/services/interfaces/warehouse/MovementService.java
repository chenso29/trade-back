package src.main.java.com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.entity.warehouse.Movement;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface MovementService extends AbstractService<MovementDto>, SearchableService<Movement, MovementDto> {


    void moveToRecyclebin(long id);

    void restoreFromRecyclebin(long id);

}
