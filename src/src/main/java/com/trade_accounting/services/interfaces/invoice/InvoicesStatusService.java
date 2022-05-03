package src.main.java.com.trade_accounting.services.interfaces.invoice;

import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import com.trade_accounting.services.interfaces.util.AbstractService;


public interface InvoicesStatusService extends AbstractService<InvoicesStatusDto> {

    InvoicesStatusDto getByName(String statusName );
}
