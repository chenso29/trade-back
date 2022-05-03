package src.main.java.com.trade_accounting.services.interfaces.production;

import com.trade_accounting.models.entity.production.StagesProduction;
import com.trade_accounting.models.dto.production.StagesProductionDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;


public interface StagesProductionService extends AbstractService<StagesProductionDto>, SearchableService<StagesProduction, StagesProductionDto> {

    List<StagesProductionDto> search(String searchTerm);


}
