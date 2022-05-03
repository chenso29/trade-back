package src.test.java.com.trade_accounting.Stubs.dto.production;

import com.trade_accounting.models.dto.production.TechnicalCardDto;
import com.trade_accounting.Stubs.model.production.TechnicalCardModelStubs;
import com.trade_accounting.utils.mapper.production.TechnicalCardMapper;
import org.mapstruct.factory.Mappers;

public class TechnicalCardDtoStubs {
    private static final TechnicalCardMapper mapper = Mappers.getMapper(TechnicalCardMapper.class);

    public static TechnicalCardDto getDto(Long id) {
        return mapper.toDto(TechnicalCardModelStubs.getTechnicalCard(id));
    }
}
