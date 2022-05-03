package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.model.warehouse.ProductPriceModelStubs;
import com.trade_accounting.models.dto.warehouse.ProductPriceDto;
import com.trade_accounting.utils.mapper.warehouse.ProductPriceMapper;
import org.mapstruct.factory.Mappers;

public class ProductPriceDtoStubs {

    private static final ProductPriceMapper mapper = Mappers.getMapper(ProductPriceMapper.class);

    public static ProductPriceDto getDto(Long id) {
        return mapper.toDto(ProductPriceModelStubs.getProductPrice(id));
    }
}
