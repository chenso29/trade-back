package src.test.java.com.trade_accounting.Stubs.dto.invoice;

import com.trade_accounting.Stubs.model.invoice.InternalOrderModelStubs;
import com.trade_accounting.models.dto.invoice.InternalOrderDto;
import com.trade_accounting.utils.mapper.invoice.InternalOrderMapper;
import org.mapstruct.factory.Mappers;

public class InternalOrderDtoStubs {
    private static final InternalOrderMapper mapper = Mappers.getMapper(InternalOrderMapper.class);

    public static InternalOrderDto getDto(Long id) {
        return mapper.toDto(InternalOrderModelStubs.getInternalOrder(id));
    }
}
