package src.test.java.com.trade_accounting.Stubs.dto.production;

import com.trade_accounting.models.dto.production.TechnicalCardGroupDto;
import com.trade_accounting.Stubs.model.production.TechnicalCardGroupModelStubs;
import com.trade_accounting.utils.mapper.production.TechnicalCardGroupMapper;
import org.mapstruct.factory.Mappers;

public class TechnicalCardGroupDtoStubs {
    private static final TechnicalCardGroupMapper mapper = Mappers.getMapper(TechnicalCardGroupMapper.class);

    public static TechnicalCardGroupDto getDto(Long id) {
        return mapper.toDto(TechnicalCardGroupModelStubs.getTechnicalCardGroup(id));
    }
}
