package src.test.java.com.trade_accounting.Stubs.dto.production;

import com.trade_accounting.models.dto.production.StagesProductionDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.production.StagesProductionMapper;
import org.mapstruct.factory.Mappers;

public class StagesProductionDtoStubs {
    private static final StagesProductionMapper mapper = Mappers.getMapper(StagesProductionMapper.class);
    public static StagesProductionDto getStagesProductionDto(Long id) {
        return mapper.toDto(ModelStubs.getStagesProduction(id));
    }
}
