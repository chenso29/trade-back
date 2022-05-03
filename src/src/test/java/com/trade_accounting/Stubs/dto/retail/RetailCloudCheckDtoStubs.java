package src.test.java.com.trade_accounting.Stubs.dto.retail;

import com.trade_accounting.Stubs.model.retail.RetailCloudCheckModelStubs;
import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.utils.mapper.retail.RetailCloudCheckMapper;
import org.mapstruct.factory.Mappers;


public class RetailCloudCheckDtoStubs {
    private static final RetailCloudCheckMapper mapper = Mappers.getMapper(RetailCloudCheckMapper.class);

    public static RetailCloudCheckDto getDto(Long id) {
        return mapper.toDto(RetailCloudCheckModelStubs.getRetailCloudCheckModelStubs(id));
    }
}
