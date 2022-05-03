package src.test.java.com.trade_accounting.Stubs.model.retail;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.retail.RetailMaking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RetailMakingModelStubs {

    public static RetailMaking getRetailMakingModelStub(Long id){
        return RetailMaking.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .retailStore(ModelStubs.getRetailStore(1L))
                .fromWhom("f")
                .company(ModelStubs.getCompany(1L))
                .sum(new BigDecimal(1000))
                .isPrint(false)
                .isSent(false)
                .comment("dd")
                .build();
    }
}
