package src.test.java.com.trade_accounting.Stubs.dto.retail;

import com.trade_accounting.Stubs.model.retail.RetailShiftModelStubs;
import com.trade_accounting.models.dto.retail.RetailShiftDto;
import com.trade_accounting.utils.mapper.retail.RetailShiftMapper;
import org.mapstruct.factory.Mappers;

public class RetailShiftDtoStubs {
    private static final RetailShiftMapper mapper = Mappers.getMapper(RetailShiftMapper.class);

    public static RetailShiftDto getDto(Long id) {
        return mapper.toDto(RetailShiftModelStubs.getRetailShiftModelStubs(id));
    }
}
