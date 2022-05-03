package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.model.warehouse.MovementModelStubs;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.utils.mapper.warehouse.MovementMapper;
import org.mapstruct.factory.Mappers;

public class MovementDtoStubs {
    private static final MovementMapper mapper = Mappers.getMapper(MovementMapper.class);

    public static MovementDto getMovementDto(Long id) {
        return mapper.toDto(MovementModelStubs.getMovement(id));
    }
}


