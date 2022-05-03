package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.model.warehouse.AcceptanceModelStubs;
import com.trade_accounting.models.dto.warehouse.AcceptanceDto;
import com.trade_accounting.utils.mapper.warehouse.AcceptanceMapper;
import org.mapstruct.factory.Mappers;

public class AcceptanceDtoStubs {
    private static final AcceptanceMapper mapper = Mappers.getMapper(AcceptanceMapper.class);

    public static AcceptanceDto getAcceptanceDto(Long id) {
        return mapper.toDto(AcceptanceModelStubs.getAcceptance(id));
    }

}
