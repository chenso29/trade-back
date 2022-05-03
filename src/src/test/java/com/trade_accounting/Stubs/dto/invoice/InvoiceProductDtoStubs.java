package src.test.java.com.trade_accounting.Stubs.dto.invoice;

import com.trade_accounting.Stubs.model.invoice.InvoiceProductModelStubs;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import com.trade_accounting.utils.mapper.invoice.InvoiceProductMapper;
import org.mapstruct.factory.Mappers;

public class InvoiceProductDtoStubs {
    private static final InvoiceProductMapper mapper = Mappers.getMapper(InvoiceProductMapper.class);

    public static InvoiceProductDto getInvoiceProductDto(Long id) {
        return mapper.toDto(InvoiceProductModelStubs.getInvoiceProduct(id));
    }
}
