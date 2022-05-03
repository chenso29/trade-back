package src.main.java.com.trade_accounting.models.entity.invoice;

import com.trade_accounting.models.entity.warehouse.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Класс-модель список товаров накладной
 *
 * @param amount - количество товаров (дефолт = 0)
 * @param price  - цена (дефолт = 0)
 * @author Sanych
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "invoice_product")
public class InvoiceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @NotNull
    @Column(name = "amount")
    @ColumnDefault("0")
    private BigDecimal amount;

    @NotNull
    @Column(name = "price")
    @ColumnDefault("0")
    private BigDecimal price;

    public InvoiceProduct(@NotNull Invoice invoice, @NotNull Product product,
                          @NotNull BigDecimal amount, @NotNull BigDecimal price) {
        this.invoice = invoice;
        this.product = product;
        this.amount = amount;
        this.price = price;
    }
}
