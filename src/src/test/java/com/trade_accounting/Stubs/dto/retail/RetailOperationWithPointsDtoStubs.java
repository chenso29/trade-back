package src.test.java.com.trade_accounting.Stubs.dto.retail;

import com.trade_accounting.models.dto.retail.RetailOperationWithPointsDto;
import com.trade_accounting.Stubs.model.retail.RetailOperationWithPointsModelStubs;
import com.trade_accounting.utils.mapper.retail.RetailOperationWithPointsMapper;
import org.mapstruct.factory.Mappers;

public class RetailOperationWithPointsDtoStubs {

    private static final RetailOperationWithPointsMapper mapper = Mappers.getMapper(RetailOperationWithPointsMapper.class);

    public static RetailOperationWithPointsDto getDto(Long id) {
        return mapper.toDto(RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(id));
    }
}
