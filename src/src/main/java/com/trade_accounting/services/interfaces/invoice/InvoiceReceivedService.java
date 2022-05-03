package src.main.java.com.trade_accounting.services.interfaces.invoice;

import com.trade_accounting.models.dto.invoice.InvoiceReceivedDto;
import com.trade_accounting.models.entity.invoice.InvoiceReceived;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface InvoiceReceivedService extends AbstractService<InvoiceReceivedDto>, SearchableService<InvoiceReceived, InvoiceReceivedDto> {

    List<InvoiceReceivedDto> searchString(String search);

    List<InvoiceReceivedDto> search(Specification<InvoiceReceived> spec);
}
