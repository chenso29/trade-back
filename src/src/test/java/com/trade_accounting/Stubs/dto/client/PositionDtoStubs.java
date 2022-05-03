package src.test.java.com.trade_accounting.Stubs.dto.client;

import com.trade_accounting.models.dto.client.PositionDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.client.PositionMapper;
import org.mapstruct.factory.Mappers;

public class PositionDtoStubs {
    private static final PositionMapper mapper = Mappers.getMapper(PositionMapper.class);
    public static PositionDto getPositionDto(Long id) {
        return mapper.toDto(
                ModelStubs.getPosition(id));
    }
}
