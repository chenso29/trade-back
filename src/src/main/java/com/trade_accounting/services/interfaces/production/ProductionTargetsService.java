package src.main.java.com.trade_accounting.services.interfaces.production;


import com.trade_accounting.models.entity.production.ProductionTargets;
import com.trade_accounting.models.dto.production.ProductionTargetsDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface ProductionTargetsService extends AbstractService<ProductionTargetsDto>, SearchableService<ProductionTargets,
        ProductionTargetsDto> {

    List<ProductionTargetsDto> search(String searchTerm);

}
