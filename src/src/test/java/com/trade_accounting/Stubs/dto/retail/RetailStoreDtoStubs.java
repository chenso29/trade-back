package src.test.java.com.trade_accounting.Stubs.dto.retail;

import com.trade_accounting.models.dto.retail.RetailStoreDto;
import com.trade_accounting.Stubs.model.retail.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.retail.RetailStoreMapper;
import org.mapstruct.factory.Mappers;

public class RetailStoreDtoStubs {
    private static final RetailStoreMapper mapper = Mappers.getMapper(RetailStoreMapper.class);

    public static RetailStoreDto getDto(Long id) {
        return mapper.toDto(RetailStoreModelStubs.getRetailStore(id));
    }
}
