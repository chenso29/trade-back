package src.test.java.com.trade_accounting.Stubs.dto.production;

import com.trade_accounting.models.dto.production.ProductionDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.production.ProductionMapper;
import org.mapstruct.factory.Mappers;

public class ProductionDtoStubs {
    private static final ProductionMapper mapper = Mappers.getMapper(ProductionMapper.class);
    public static ProductionDto getProductionDto(Long id) {
        return mapper.toDto(ModelStubs.getProduction(id));
    }
}
