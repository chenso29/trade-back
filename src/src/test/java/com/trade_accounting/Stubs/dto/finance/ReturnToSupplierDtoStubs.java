package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.models.dto.finance.ReturnToSupplierDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.finance.ReturnToSupplierMapper;
import org.mapstruct.factory.Mappers;

public class ReturnToSupplierDtoStubs {
    private static final ReturnToSupplierMapper mapper = Mappers.getMapper(ReturnToSupplierMapper.class);

    public static ReturnToSupplierDto getReturnToSupplierDto(Long id) {
        return mapper.toDto(ModelStubs.getReturnToSupplier(id));
    }

}
