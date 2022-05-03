package src.main.java.com.trade_accounting.models.entity.warehouse;

import com.trade_accounting.models.entity.company.SupplierAccount;
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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "supplier_account_products_list")
public class SupplierAccountProductsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private SupplierAccount supplierAccount;

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

    @NotNull
    @Column(name = "sum")
    @ColumnDefault("0")
    private BigDecimal sum;

    @NotNull
    @Column(name = "percentNds")
    @ColumnDefault("20")
    private String percentNds;

    @NotNull
    @Column(name = "nds")
    @ColumnDefault("0")
    private BigDecimal nds;

    @NotNull
    @Column(name = "total")
    @ColumnDefault("0")
    private BigDecimal total;

    public SupplierAccountProductsList(@NotNull SupplierAccount supplierAccount, @NotNull Product product,
                                       @NotNull BigDecimal amount, @NotNull BigDecimal price, @NotNull BigDecimal sum,
                                       @NotNull String percentNds, @NotNull BigDecimal nds, @NotNull BigDecimal total) {
        this.supplierAccount = supplierAccount;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.sum = sum;
        this.percentNds = percentNds;
        this.nds = nds;
        this.total = total;
    }
}
