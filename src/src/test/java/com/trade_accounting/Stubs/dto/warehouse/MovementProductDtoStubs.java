package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.model.warehouse.MovementProductModelStubs;
import com.trade_accounting.models.dto.warehouse.MovementProductDto;
import com.trade_accounting.utils.mapper.warehouse.MovementProductMapper;
import org.mapstruct.factory.Mappers;

public class MovementProductDtoStubs {

    private static final MovementProductMapper mapper = Mappers.getMapper(MovementProductMapper.class);

    public static MovementProductDto getMovementProductDto(Long id) {
        return mapper.toDto(MovementProductModelStubs.getMovementProduct(id));
    }
}
