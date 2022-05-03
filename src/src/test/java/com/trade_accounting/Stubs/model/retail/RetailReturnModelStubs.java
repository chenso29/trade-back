package src.test.java.com.trade_accounting.Stubs.model.retail;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.retail.RetailReturn;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RetailReturnModelStubs {

    public static RetailReturn getRetailReturn(Long id) {
        return RetailReturn.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .retailStore(ModelStubs.getRetailStore(1L))
                .cashAmount(new BigDecimal(1000))
                .cashlessAmount(new BigDecimal(1000))
                .isSpend(false)
                .isSend(false)
                .isPrint(false)
                .comment("Комент 1")
                .build();
    }
}
