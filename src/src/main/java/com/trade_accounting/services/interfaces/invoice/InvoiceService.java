package src.main.java.com.trade_accounting.services.interfaces.invoice;

import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceService extends AbstractService<InvoiceDto>, SearchableService<Invoice, InvoiceDto> {

    List<InvoiceDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice);

    @Transactional
    default List<InvoiceDto> getAll(String typeOfInvoice) {
        return search((root, query, builder)
                -> builder.equal(root.get("typeOfInvoice"), TypeOfInvoice.valueOf(typeOfInvoice)));
    }

    List<InvoiceDto> getFromDateTime(LocalDateTime dateTime);

    List<InvoiceDto> getByContractorId(Long id);
    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);
}
