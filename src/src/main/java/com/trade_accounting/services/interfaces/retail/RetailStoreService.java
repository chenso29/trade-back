package src.main.java.com.trade_accounting.services.interfaces.retail;

import com.trade_accounting.models.dto.retail.RetailStoreDto;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface RetailStoreService extends AbstractService<RetailStoreDto>, SearchableService<RetailStore, RetailStoreDto> {
    List<RetailStoreDto> search(String searchTerm);
}
