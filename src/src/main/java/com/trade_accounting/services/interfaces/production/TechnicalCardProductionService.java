package src.main.java.com.trade_accounting.services.interfaces.production;

import com.trade_accounting.models.entity.production.TechnicalCardProduction;
import com.trade_accounting.models.dto.production.TechnicalCardProductionDto;
import com.trade_accounting.services.interfaces.util.AbstractService;

import java.util.List;

public interface TechnicalCardProductionService extends AbstractService<TechnicalCardProductionDto> {
    List<TechnicalCardProduction> finaAllById(List<Long> id);
}
