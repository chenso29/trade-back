package src.main.java.com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.entity.purchases.PurchaseControl;
import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;
import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import com.trade_accounting.models.entity.purchases.PurchaseHistoryOfSales;
import com.trade_accounting.models.entity.warehouse.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseControlMapper {
    /**
     * @return PurchaseControl
     */

    default PurchaseControl toModel(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }

        PurchaseControl.PurchaseControlBuilder purchaseControl = PurchaseControl.builder();

        purchaseControl.historyOfSales(purchaseControlDtoToPurchaseHistoryOfSales(purchaseControlDto));
        purchaseControl.currentBalance(purchaseControlDtoToPurchaseCurrentBalance(purchaseControlDto));
        purchaseControl.forecast(purchaseControlDtoToPurchaseForecast(purchaseControlDto));
        purchaseControl.id(purchaseControlDto.getId());

        purchaseControl.product(purchaseControlDtoToProduct(purchaseControlDto));

        purchaseControl.productCode(purchaseControlDto.getProductCode());
        purchaseControl.articleNumber(purchaseControlDto.getArticleNumber());
        purchaseControl.productMeasure(purchaseControlDto.getProductMeasure());
        purchaseControl.productQuantity(purchaseControlDto.getProductQuantity());

        return purchaseControl.build();
    }

    private PurchaseHistoryOfSales purchaseControlDtoToPurchaseHistoryOfSales(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }

        PurchaseHistoryOfSales.PurchaseHistoryOfSalesBuilder purchaseHistoryOfSales = PurchaseHistoryOfSales.builder();

        purchaseHistoryOfSales.id(purchaseControlDto.getHistoryOfSalesId());

        return purchaseHistoryOfSales.build();
    }

    private PurchaseCurrentBalance purchaseControlDtoToPurchaseCurrentBalance(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }

        PurchaseCurrentBalance.PurchaseCurrentBalanceBuilder purchaseCurrentBalance = PurchaseCurrentBalance.builder();

        purchaseCurrentBalance.id(purchaseControlDto.getCurrentBalanceId());

        return purchaseCurrentBalance.build();
    }

    private PurchaseForecast purchaseControlDtoToPurchaseForecast(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }

        PurchaseForecast.PurchaseForecastBuilder purchaseForecast = PurchaseForecast.builder();

        purchaseForecast.id(purchaseControlDto.getForecastId());

        return purchaseForecast.build();
    }
    private Product purchaseControlDtoToProduct(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }
        Product.ProductBuilder productBuilder = Product.builder();
        productBuilder.id(purchaseControlDto.getProductNameId());
        return productBuilder.build();
    }

    /**
     * @return PurchaseControlDto
     */
//    @Mappings({
//            @Mapping(source = "historyOfSales.id", target = "historyOfSalesId"),
//            @Mapping(source = "currentBalance.id", target = "currentBalanceId"),
//            @Mapping(source = "forecast.id", target = "forecastId")
//    })
    default PurchaseControlDto toDto(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }

        PurchaseControlDto.PurchaseControlDtoBuilder purchaseControlDto = PurchaseControlDto.builder();

        purchaseControlDto.historyOfSalesId(purchaseControlHistoryOfSalesId(purchaseControl));
        purchaseControlDto.currentBalanceId(purchaseControlCurrentBalanceId(purchaseControl));
        purchaseControlDto.forecastId(purchaseControlForecastId(purchaseControl));
        purchaseControlDto.id(purchaseControl.getId());

        purchaseControlDto.productNameId(purchaseControlProductId(purchaseControl));

        purchaseControlDto.productCode(purchaseControl.getProductCode());
        purchaseControlDto.articleNumber(purchaseControl.getArticleNumber());
        purchaseControlDto.productMeasure(purchaseControl.getProductMeasure());
        purchaseControlDto.productQuantity(purchaseControl.getProductQuantity());

        return purchaseControlDto.build();
    }

    private Long purchaseControlProductId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        Product product = purchaseControl.getProduct();
        if (product == null) {
            return null;
        }
        Long id = product.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlHistoryOfSalesId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        PurchaseHistoryOfSales historyOfSales = purchaseControl.getHistoryOfSales();
        if (historyOfSales == null) {
            return null;
        }
        Long id = historyOfSales.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlCurrentBalanceId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        PurchaseCurrentBalance currentBalance = purchaseControl.getCurrentBalance();
        if (currentBalance == null) {
            return null;
        }
        Long id = currentBalance.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlForecastId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        PurchaseForecast forecast = purchaseControl.getForecast();
        if (forecast == null) {
            return null;
        }
        Long id = forecast.getId();
        if (id == null) {
            return null;
        }
        return id;
    }
}
