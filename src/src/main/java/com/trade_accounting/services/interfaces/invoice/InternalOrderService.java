package src.main.java.com.trade_accounting.services.interfaces.invoice;
import com.trade_accounting.models.entity.invoice.InternalOrder;
import com.trade_accounting.models.dto.invoice.InternalOrderDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface InternalOrderService extends AbstractService<InternalOrderDto>, SearchableService<InternalOrder, InternalOrderDto> {
    List<InternalOrderDto> getAll(String searchItem);
    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);
}
