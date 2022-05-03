package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.models.dto.warehouse.InventarizationProductDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.warehouse.InventarizationProductMapper;
import org.mapstruct.factory.Mappers;

public class InventarizationProductDtoStubs {
    private static final InventarizationProductMapper mapper = Mappers.getMapper(InventarizationProductMapper.class);

    public static InventarizationProductDto getInventarizationProductDto(Long id) {
        return mapper.toDto(ModelStubs.getInventarizationProduct(id));
    }
}
