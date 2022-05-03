package src.test.java.com.trade_accounting.Stubs.model.finance;


import com.trade_accounting.models.entity.finance.Loss;

import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.Stubs.ModelStubs.getWarehouse;
import static com.trade_accounting.Stubs.model.finance.LossProductModelStubs.getLossProduct;

public class LossModelStubs {
    public static Loss getLoss(Long id) {
        return Loss.builder()
                .id(id)
                .lossProducts(List.of(
                        getLossProduct(1L),
                        getLossProduct(2L),
                        getLossProduct(3L)
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
