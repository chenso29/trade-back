package src.test.java.com.trade_accounting.Stubs.model.invoice;

import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.Stubs.ModelStubs;

import java.math.BigDecimal;

import static com.trade_accounting.Stubs.model.invoice.InvoiceModelStubs.getInvoice;

public class InvoiceProductModelStubs {
    public static InvoiceProduct getInvoiceProduct(Long id) {
        return InvoiceProduct.builder()
                .id(id)
                .invoice(getInvoice(id))
                .product(ModelStubs.getProduct(id))
                .amount(BigDecimal.ONE)
                .price(BigDecimal.ONE)
                .build();
    }
}
