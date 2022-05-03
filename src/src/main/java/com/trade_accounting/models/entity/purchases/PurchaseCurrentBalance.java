package src.main.java.com.trade_accounting.models.entity.purchases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс-модель текущий остаток в закупках
 *
 * @param id                        - номер остатка товара
 * @param restOfTheWarehouse        - остаток на складе
 * @param productsReserve           - резерв товара
 * @param productsAwaiting          - товара в ожидании
 * @param productsAvailableForOrder - товара доступно для заказа
 * @param daysStoreOnTheWarehouse   - дни хранения на складе
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchase_current_balance")
public class PurchaseCurrentBalance {
    @Id
    @NotNull
    private Long id;

    private Long restOfTheWarehouse;

    private Long productsReserve;

    private Long productsAwaiting;

    private Long productsAvailableForOrder;

    private Long daysStoreOnTheWarehouse;
}