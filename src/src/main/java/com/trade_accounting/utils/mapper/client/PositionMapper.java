package src.main.java.com.trade_accounting.utils.mapper.client;

import com.trade_accounting.models.entity.client.Position;
import com.trade_accounting.models.dto.client.PositionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    //Position
    PositionDto toDto(Position position);

    Position toModel(PositionDto position);
}
