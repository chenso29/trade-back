package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.SupplierAccountDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.SupplierAccountMapper;
import org.mapstruct.factory.Mappers;

public class SupplierAccountDtoStubs {
    public static final SupplierAccountMapper mapper = Mappers.getMapper(SupplierAccountMapper.class);
    public static SupplierAccountDto getSupplierAccountDto(Long id) {
        return mapper.toDto(ModelStubs.getSupplierAccount(id));
    }
}
