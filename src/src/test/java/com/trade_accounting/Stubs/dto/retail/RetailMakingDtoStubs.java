package src.test.java.com.trade_accounting.Stubs.dto.retail;

import com.trade_accounting.Stubs.model.retail.RetailMakingModelStubs;
import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.models.entity.retail.RetailMaking;
import com.trade_accounting.utils.mapper.retail.RetailMakingMapper;
import org.mapstruct.factory.Mappers;

public class RetailMakingDtoStubs {
    private static final RetailMakingMapper mapper = Mappers.getMapper(RetailMakingMapper.class);

    public static RetailMakingDto getDto(Long id) {
        RetailMaking retailMakingModelStub = RetailMakingModelStubs.getRetailMakingModelStub(id);
        return mapper.toDto(retailMakingModelStub);
    }
}
