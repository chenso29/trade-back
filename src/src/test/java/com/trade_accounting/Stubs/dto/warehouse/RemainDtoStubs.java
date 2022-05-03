package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.models.dto.warehouse.RemainDto;
import com.trade_accounting.Stubs.model.warehouse.RemainModelStubs;
import com.trade_accounting.utils.mapper.warehouse.RemainMapper;
import org.mapstruct.factory.Mappers;

public class RemainDtoStubs {
    private static final RemainMapper mapper = Mappers.getMapper(RemainMapper.class);

    public static RemainDto getDto(Long id){
        return mapper.toDto(RemainModelStubs.getRemain(id));
    }
}
