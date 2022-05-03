package src.main.java.com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;
import com.trade_accounting.models.dto.purchases.PurchaseCurrentBalanceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseCurrentBalanceMapper {
    /**
     * @return PurchaseCurrentBalance
     */


    default PurchaseCurrentBalance toModel(PurchaseCurrentBalanceDto purchaseCurrentBalanceDto) {
        if(purchaseCurrentBalanceDto == null) {
            return null;
        }
        return PurchaseCurrentBalance.builder()
                .id(purchaseCurrentBalanceDto.getId())
                .restOfTheWarehouse(purchaseCurrentBalanceDto.getRestOfTheWarehouse())
                .productsReserve(purchaseCurrentBalanceDto.getProductsReserve())
                .productsAwaiting(purchaseCurrentBalanceDto.getProductsAwaiting())
                .productsAvailableForOrder(purchaseCurrentBalanceDto.getProductsAvailableForOrder())
                .daysStoreOnTheWarehouse(purchaseCurrentBalanceDto.getDaysStoreOnTheWarehouse())
                .build();

    }

    default PurchaseCurrentBalanceDto toDto(PurchaseCurrentBalance purchaseCurrentBalance) {
        PurchaseCurrentBalanceDto purchaseCurrentBalanceDto = new PurchaseCurrentBalanceDto();
        if (purchaseCurrentBalance == null) {
            return null;
        }

        purchaseCurrentBalanceDto.setId(purchaseCurrentBalance.getId());
        purchaseCurrentBalanceDto.setRestOfTheWarehouse(purchaseCurrentBalance.getRestOfTheWarehouse());
        purchaseCurrentBalanceDto.setProductsReserve(purchaseCurrentBalance.getProductsReserve());
        purchaseCurrentBalanceDto.setProductsAwaiting(purchaseCurrentBalance.getProductsAwaiting());
        purchaseCurrentBalanceDto.setProductsAvailableForOrder(purchaseCurrentBalance.getProductsAvailableForOrder());
        purchaseCurrentBalanceDto.setDaysStoreOnTheWarehouse(purchaseCurrentBalance.getDaysStoreOnTheWarehouse());

        return purchaseCurrentBalanceDto;
    }
}
