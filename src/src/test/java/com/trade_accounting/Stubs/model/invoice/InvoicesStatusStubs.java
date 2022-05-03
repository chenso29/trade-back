package src.test.java.com.trade_accounting.Stubs.model.invoice;

import com.trade_accounting.models.entity.invoice.InvoicesStatus;

public class InvoicesStatusStubs {
    public static InvoicesStatus getInvoicesStatus(Long id) {
        return InvoicesStatus.builder()
                .id(id)
                .statusName("Новый" + id)
                .build();
    }
}
