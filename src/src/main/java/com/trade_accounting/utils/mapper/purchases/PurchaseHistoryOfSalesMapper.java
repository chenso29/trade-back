package src.main.java.com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.models.entity.purchases.PurchaseHistoryOfSales;
import com.trade_accounting.models.dto.purchases.PurchaseHistoryOfSalesDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseHistoryOfSalesMapper {
    /**
     * @return PurchaseHistoryOfSales
     */


    default PurchaseHistoryOfSales toModel(PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto) {
        if (purchaseHistoryOfSalesDto == null) {
            return null;
        }

        return PurchaseHistoryOfSales.builder()
                .id(purchaseHistoryOfSalesDto.getId())
                .sumOfProducts(purchaseHistoryOfSalesDto.getSumOfProducts())
                .productPrice(purchaseHistoryOfSalesDtoToProductPrice(purchaseHistoryOfSalesDto))
                .productMargin(purchaseHistoryOfSalesDto.getProductMargin())
                .productProfitMargin(purchaseHistoryOfSalesDto.getProductProfitMargin())
                .productSalesPerDay(purchaseHistoryOfSalesDto.getProductSalesPerDay())
                .build();

    }

    default PurchaseHistoryOfSalesDto toDto(PurchaseHistoryOfSales purchaseHistoryOfSales) {
        PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto = new PurchaseHistoryOfSalesDto();
        if (purchaseHistoryOfSales == null) {
            return null;
        }

        purchaseHistoryOfSalesDto.setId(purchaseHistoryOfSales.getId());
        purchaseHistoryOfSalesDto.setSumOfProducts(purchaseHistoryOfSales.getSumOfProducts());
        purchaseHistoryOfSalesDto.setProductPriceId(purchaseHistoryOfSalesProductPrice(purchaseHistoryOfSales));
        purchaseHistoryOfSalesDto.setProductProfitMargin(purchaseHistoryOfSales.getProductProfitMargin());
        purchaseHistoryOfSalesDto.setProductMargin(purchaseHistoryOfSales.getProductMargin());
        purchaseHistoryOfSalesDto.setProductSalesPerDay(purchaseHistoryOfSales.getProductSalesPerDay());

        return purchaseHistoryOfSalesDto;
    }

    private ProductPrice purchaseHistoryOfSalesDtoToProductPrice(PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto) {
        return ProductPrice.builder()
                .id(purchaseHistoryOfSalesDto.getProductPriceId()).build();
    }

    private Long purchaseHistoryOfSalesProductPrice(PurchaseHistoryOfSales purchaseHistoryOfSales) {
        if (purchaseHistoryOfSales == null) {
            return null;
        }
        ProductPrice productPrice = purchaseHistoryOfSales.getProductPrice();
        if (productPrice == null) {
            return null;
        }
        Long id = productPrice.getId();
        if (id == null) {
            return null;
        }
        return id;
    }
}
