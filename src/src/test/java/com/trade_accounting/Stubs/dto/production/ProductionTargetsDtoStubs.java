package src.test.java.com.trade_accounting.Stubs.dto.production;

import com.trade_accounting.models.dto.production.ProductionTargetsDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.production.ProductionTargetsMapper;
import org.mapstruct.factory.Mappers;

public class ProductionTargetsDtoStubs {
    private static final ProductionTargetsMapper mapper = Mappers.getMapper(ProductionTargetsMapper.class);
    public static ProductionTargetsDto getProductionTargetsDto(Long id) {
        return mapper.toDto(ModelStubs.getProductionTargets(id));
    }
}
