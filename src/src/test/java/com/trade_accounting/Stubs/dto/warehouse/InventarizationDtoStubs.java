package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.models.dto.warehouse.InventarizationDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.warehouse.InventarizationMapper;
import org.mapstruct.factory.Mappers;

public class InventarizationDtoStubs {
    private static final InventarizationMapper mapper = Mappers.getMapper(InventarizationMapper.class);

    public static InventarizationDto getInventarizationDto(Long id) {
        return mapper.toDto(ModelStubs.getInventarization(id));
    }
}
