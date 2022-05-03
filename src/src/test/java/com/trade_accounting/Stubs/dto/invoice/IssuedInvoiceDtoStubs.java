package src.test.java.com.trade_accounting.Stubs.dto.invoice;

import com.trade_accounting.Stubs.model.invoice.IssuedInvoiceModelStubs;
import com.trade_accounting.models.dto.invoice.IssuedInvoiceDto;
import com.trade_accounting.utils.mapper.invoice.IssuedInvoiceMapper;
import org.mapstruct.factory.Mappers;

public class IssuedInvoiceDtoStubs {
    private static final IssuedInvoiceMapper mapper = Mappers.getMapper(IssuedInvoiceMapper.class);

    public static IssuedInvoiceDto getDto(Long id) {
        return mapper.toDto(IssuedInvoiceModelStubs.getIssuedInvoice(id));
    }
}
