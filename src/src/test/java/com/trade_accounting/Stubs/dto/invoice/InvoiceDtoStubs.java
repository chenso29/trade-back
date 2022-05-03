package src.test.java.com.trade_accounting.Stubs.dto.invoice;

import com.trade_accounting.Stubs.model.invoice.InvoiceModelStubs;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.utils.mapper.invoice.InvoiceMapper;
import org.mapstruct.factory.Mappers;

public class InvoiceDtoStubs {
    private static final InvoiceMapper mapper = Mappers.getMapper(InvoiceMapper.class);

    public static InvoiceDto getInvoiceDto(Long id) {
        return mapper.toDto(
                InvoiceModelStubs.getInvoice(id)
        );
    }
}
