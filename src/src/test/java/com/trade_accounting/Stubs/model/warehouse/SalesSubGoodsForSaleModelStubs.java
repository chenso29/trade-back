package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;

import java.math.BigDecimal;

public class SalesSubGoodsForSaleModelStubs {
    public static SalesSubGoodsForSale getSalesSubGoodsForSale(Long id){
        return SalesSubGoodsForSale.builder()
                .id(id)
                .name(new Product())
                .code(new Long(111))
                .vendorCode(new Long(110))
                .transferred(1)
                .accepted(2)
                .amount(new Long(10))
                .sum(new BigDecimal(1000))
                .returned(new Long (10))
                .remainder(new BigDecimal(1000))
                .reportAmount(new Long(9))
                .reportSum(new BigDecimal(1000))
                .notReportAmount(new Long(100))
                .notReportSum(new BigDecimal(1000))
                .build();
    }
}
