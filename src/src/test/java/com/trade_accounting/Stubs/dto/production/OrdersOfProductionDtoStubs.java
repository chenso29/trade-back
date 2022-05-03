package src.test.java.com.trade_accounting.Stubs.dto.production;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.production.OrdersOfProductionDto;
import com.trade_accounting.utils.mapper.production.OrdersOfProductionMapper;
import org.mapstruct.factory.Mappers;

public class OrdersOfProductionDtoStubs {
    private static final OrdersOfProductionMapper mapper = Mappers.getMapper(OrdersOfProductionMapper.class);
    public static OrdersOfProductionDto getOrdersOfProductionDto(Long id) { return mapper.toDto(ModelStubs.getOrdersOfProduction(id));}
}
