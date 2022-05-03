package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.model.warehouse.AcceptanceProductionModelStubs;
import com.trade_accounting.models.dto.warehouse.AcceptanceProductionDto;
import com.trade_accounting.utils.mapper.warehouse.AcceptanceProductionMapper;
import org.mapstruct.factory.Mappers;

public class AcceptanceProductionDtoStubs {
    private static final AcceptanceProductionMapper mapper = Mappers.getMapper(AcceptanceProductionMapper.class);
    public static AcceptanceProductionDto getAcceptanceProductionDto(Long id) {
        return mapper.toDto(AcceptanceProductionModelStubs.getAcceptanceProduction(id));
    }
}
