package src.main.java.com.trade_accounting.models.entity.purchases;

import com.trade_accounting.models.entity.warehouse.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * Класс-модель история продаж в закупках
 *
 * @param id                 - номер в истории заказов
 * @param sumOfProducts      - сумма товаров
 * @param productPrice       - цена товара
 * @param productMargin      - прибыль, маржа
 * @param productSalesPerDay - количество продаж в день
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchase_history_of_sales")
public class PurchaseHistoryOfSales {
    @Id
    @NotNull
    private Long id;

    private BigDecimal sumOfProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductPrice productPrice;

    private BigDecimal productMargin;//она же прибыль, она же маржа

    private BigDecimal productProfitMargin;//она же profit, она же рентабельность

    private Long productSalesPerDay;
}