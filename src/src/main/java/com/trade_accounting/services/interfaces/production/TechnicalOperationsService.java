package src.main.java.com.trade_accounting.services.interfaces.production;

import com.trade_accounting.models.entity.production.TechnicalOperations;
import com.trade_accounting.models.dto.production.TechnicalOperationsDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;


public interface TechnicalOperationsService extends AbstractService<TechnicalOperationsDto>, SearchableService<TechnicalOperations, TechnicalOperationsDto> {

     List<TechnicalOperationsDto> search(String searchTerm);

//     List<TechnicalOperationsDto> searchByString(String nameFilter);

     void moveToRecyclebin(long id);
     void restoreFromRecyclebin(long id);
}
