package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import com.trade_accounting.Stubs.model.warehouse.WarehouseModelStubs;
import com.trade_accounting.utils.mapper.warehouse.WarehouseMapper;
import org.mapstruct.factory.Mappers;

public class WarehouseDtoStubs {

    private static final WarehouseMapper mapper = Mappers.getMapper(WarehouseMapper.class);

    public static WarehouseDto getDto(Long id) {
        return mapper.toDto(WarehouseModelStubs.getWarehouse(id));
    }
}
