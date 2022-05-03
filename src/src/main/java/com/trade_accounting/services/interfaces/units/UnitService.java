package src.main.java.com.trade_accounting.services.interfaces.units;

import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface UnitService extends AbstractService<UnitDto>, SearchableService<Unit, UnitDto> {

    List<UnitDto> searchByString(String text);

    void moveToRecyclebin(long id);
}
