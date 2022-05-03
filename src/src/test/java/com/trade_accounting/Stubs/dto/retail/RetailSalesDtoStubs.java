package src.test.java.com.trade_accounting.Stubs.dto.retail;

import com.trade_accounting.models.dto.retail.RetailSalesDto;
import com.trade_accounting.Stubs.model.retail.RetailSalesModelStubs;
import com.trade_accounting.utils.mapper.retail.RetailSalesMapper;
import org.mapstruct.factory.Mappers;

public class RetailSalesDtoStubs {

    private static final RetailSalesMapper mapper = Mappers.getMapper(RetailSalesMapper.class);

    public static RetailSalesDto getDto(Long id) {
        return mapper.toDto(RetailSalesModelStubs.getRetailSales(id));
    }
}
