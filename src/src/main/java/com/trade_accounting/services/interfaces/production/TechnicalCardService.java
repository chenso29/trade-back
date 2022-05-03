package src.main.java.com.trade_accounting.services.interfaces.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.dto.production.TechnicalCardDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface TechnicalCardService extends AbstractService<TechnicalCardDto>, SearchableService<TechnicalCard, TechnicalCardDto> {

    List<TechnicalCardDto> search (String searchTerm);

    List<TechnicalCardDto> getAllByTechnicalCardGroupId (Long id);
}
