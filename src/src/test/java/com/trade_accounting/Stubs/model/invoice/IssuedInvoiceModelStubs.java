package src.test.java.com.trade_accounting.Stubs.model.invoice;

import com.trade_accounting.models.entity.invoice.IssuedInvoice;

import java.time.LocalDateTime;

import static com.trade_accounting.Stubs.ModelStubs.*;

public class IssuedInvoiceModelStubs {
    public static IssuedInvoice getIssuedInvoice(Long id) {
        return IssuedInvoice.builder()
                .id(id)
                .date(LocalDateTime.now())
                .company(getCompany(1L))
                .contractor(getContractor(1L))
                .isSpend(true)
                .comment("Комментарий")
                .build();
    }
}
