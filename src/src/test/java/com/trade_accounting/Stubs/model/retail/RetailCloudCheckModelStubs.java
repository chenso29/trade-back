package src.test.java.com.trade_accounting.Stubs.model.retail;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.retail.RetailCloudCheck;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RetailCloudCheckModelStubs {
    public static RetailCloudCheck getRetailCloudCheckModelStubs(Long id){
        return RetailCloudCheck.builder()
                .id(id)
                .date(LocalDateTime.now())
                .initiator(ModelStubs.getRetailStore(1L))
                .fiscalizationPoint(ModelStubs.getRetailStore(1L))
                .status("с")
                .cheskStatus("м")
                .total(new BigDecimal(1000))
                .currency(ModelStubs.getCurrency(1L))
                .cashier(ModelStubs.getEmployee(id))
                .build();
    }
}
