package src.test.java.com.trade_accounting.Stubs.dto.production;

import com.trade_accounting.models.dto.production.RequestsProductionsDto;
import com.trade_accounting.Stubs.model.production.RequestsProductionsModelStubs;
import com.trade_accounting.utils.mapper.production.RequestsProductionsMapper;
import org.mapstruct.factory.Mappers;

public class RequestsProductionsDtoStubs {
    private static final RequestsProductionsMapper mapper = Mappers.getMapper(RequestsProductionsMapper.class);

    public static RequestsProductionsDto getDto(Long id) {
        return mapper.toDto(RequestsProductionsModelStubs.getRequestsProductions(id));
    }
}
