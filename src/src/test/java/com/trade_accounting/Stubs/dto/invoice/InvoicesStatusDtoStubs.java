package src.test.java.com.trade_accounting.Stubs.dto.invoice;

import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.invoice.InvoicesStatusMapper;
import org.mapstruct.factory.Mappers;

public class InvoicesStatusDtoStubs {
    private static final InvoicesStatusMapper invoicesStatusMapper = Mappers.getMapper(InvoicesStatusMapper.class);

    public static InvoicesStatusDto getInvoicesStatusDto(Long id) {
        return invoicesStatusMapper.toDto(ModelStubs.getInvoicesStatus(id));
    }
}
