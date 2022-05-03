package src.main.java.com.trade_accounting.services.interfaces.production;

import com.trade_accounting.models.entity.production.TechnicalProcess;
import com.trade_accounting.models.dto.production.TechnicalProcessDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface TechnicalProcessService extends AbstractService<TechnicalProcessDto>, SearchableService<TechnicalProcess, TechnicalProcessDto> {
    List<TechnicalProcessDto> search(String request);
    TechnicalProcessDto getByName(String name);
}
