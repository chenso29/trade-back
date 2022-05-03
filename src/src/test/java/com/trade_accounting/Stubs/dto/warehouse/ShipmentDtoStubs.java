package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.model.warehouse.ShipmentModelStubs;
import com.trade_accounting.models.dto.warehouse.ShipmentDto;
import com.trade_accounting.utils.mapper.warehouse.ShipmentMapper;
import org.mapstruct.factory.Mappers;

public class ShipmentDtoStubs {
    private static final ShipmentMapper mapper = Mappers.getMapper(ShipmentMapper.class);

    public static ShipmentDto getShipmentDro(Long id){
        return mapper.toDto(ShipmentModelStubs.getShipment(id));
    }
}
