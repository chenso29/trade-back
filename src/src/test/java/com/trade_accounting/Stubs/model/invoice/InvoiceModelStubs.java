package src.test.java.com.trade_accounting.Stubs.model.invoice;

import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;

import java.time.LocalDateTime;

import static com.trade_accounting.Stubs.ModelStubs.*;

public class InvoiceModelStubs {
    public static Invoice getInvoice(Long id) {
        return Invoice.builder()
                .id(id)
                .date(LocalDateTime.now())
                .typeOfInvoice(TypeOfInvoice.RECEIPT)
                .company(getCompany(id))
                .contractor(getContractor(id))
                .warehouse(getWarehouse(id))
                .isSpend(true)
                .comment("Комментарий")
                .build();
    }
}
