package src.main.java.com.trade_accounting.models.entity.purchases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс-модель прогнозы закупок
 *
 * @param id               - номер прогноза закупки
 * @param reservedDays     - дней в запасе
 * @param reservedProducts - запас товара
 * @param ordered          - заказано(да\нет)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchase_forecast")
public class PurchaseForecast {
    @Id
    @NotNull
    private Long id;

    private Long reservedDays;

    private Long reservedProducts;

    @ColumnDefault("false")
    private Boolean ordered;
}