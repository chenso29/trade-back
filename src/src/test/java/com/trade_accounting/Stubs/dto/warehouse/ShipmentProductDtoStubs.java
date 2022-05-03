package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.models.dto.warehouse.ShipmentProductDto;
import com.trade_accounting.Stubs.model.warehouse.ShipmentProductModelStubs;
import com.trade_accounting.utils.mapper.warehouse.ShipmentProductMapper;
import org.mapstruct.factory.Mappers;

public class ShipmentProductDtoStubs {
    private static final ShipmentProductMapper mapper = Mappers.getMapper(ShipmentProductMapper.class);

    public static ShipmentProductDto getDto(Long id) {
        return mapper.toDto(ShipmentProductModelStubs.getShipmentProduct(id));
    }
}
