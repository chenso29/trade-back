package src.test.java.com.trade_accounting.Stubs.model.finance;

import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import java.math.BigDecimal;

import static com.trade_accounting.Stubs.ModelStubs.*;

public class PrepaymentReturnModelStubs {

    public static PrepaymentReturn getPrepaymentReturn(Long id) {
        return PrepaymentReturn.builder().id(1L).time("")
                .retailStore(getRetailStore(1L)).contractor(getContractor(1L))
                .company(getCompany(2L)).sumCash(new BigDecimal(195)).sumNonСash(new BigDecimal(77))
                .sent(true).comment("что-либо").build();
    }
}
