package src.test.java.com.trade_accounting.Stubs.dto.retail;

import com.trade_accounting.models.dto.retail.RetailReturnDto;
import com.trade_accounting.Stubs.model.retail.RetailReturnModelStubs;
import com.trade_accounting.utils.mapper.retail.RetailReturnMapper;
import org.mapstruct.factory.Mappers;

public class RetailReturnDtoStubs {

    private static final RetailReturnMapper mapper = Mappers.getMapper(RetailReturnMapper.class);

    public static RetailReturnDto getDto(Long id) {
        return mapper.toDto(RetailReturnModelStubs.getRetailReturn(id));
    }
}
