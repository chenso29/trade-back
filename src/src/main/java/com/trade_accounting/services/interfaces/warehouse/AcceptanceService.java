package src.main.java.com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.dto.warehouse.AcceptanceDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface AcceptanceService extends AbstractService<AcceptanceDto>, SearchableService<Acceptance, AcceptanceDto> {

    List<AcceptanceDto> searchByNumberAndComment(String searchTerm);

    List<AcceptanceDto> search(String search);

    void moveToRecyclebin(long id);

    void restoreFromRecyclebin(long id);
}
