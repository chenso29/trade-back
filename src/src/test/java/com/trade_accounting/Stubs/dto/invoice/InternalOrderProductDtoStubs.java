package src.test.java.com.trade_accounting.Stubs.dto.invoice;

import com.trade_accounting.Stubs.model.invoice.InternalOrderProductModelStubs;
import com.trade_accounting.models.dto.invoice.InternalOrderProductsDto;
import com.trade_accounting.utils.mapper.invoice.InternalOrderProductMapper;
import org.mapstruct.factory.Mappers;

public class InternalOrderProductDtoStubs {
    private static final InternalOrderProductMapper mapper = Mappers.getMapper(InternalOrderProductMapper.class);

    public static InternalOrderProductsDto getDto(Long id) {
        return mapper.toDto(InternalOrderProductModelStubs.getInternalOrderProduct(id));
    }
}
