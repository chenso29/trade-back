package src.test.java.com.trade_accounting.Stubs.model.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrder;

import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.Stubs.ModelStubs.getWarehouse;

public class InternalOrderModelStubs {
    public static InternalOrder getInternalOrder(Long id) {
        return InternalOrder.builder()
                .id(id)
                .internalOrderProducts(List.of(
                        InternalOrderProductModelStubs.getInternalOrderProduct(1L),
                        InternalOrderProductModelStubs.getInternalOrderProduct(2L),
                        InternalOrderProductModelStubs.getInternalOrderProduct(3L)
                ))
                .date(LocalDateTime.now())
                .company(getCompany(1L))
                .warehouse(getWarehouse(1L))
                .isSent(false)
                .isPrint(true)
                .comment("Comment " + id)
                .build();
    }
}
