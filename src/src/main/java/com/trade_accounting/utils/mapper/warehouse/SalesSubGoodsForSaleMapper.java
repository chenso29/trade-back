package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesSubGoodsForSaleMapper {
    default SalesSubGoodsForSale toModel(SalesSubGoodsForSaleDto salesSubGoodsForSaleDto) {
        if (salesSubGoodsForSaleDto == null) {
            return null;
        } else {
            return SalesSubGoodsForSale.builder()
                    .id(salesSubGoodsForSaleDto.getId())
                    .name(salesSubGoodsForSaleDtoToProduct(salesSubGoodsForSaleDto))
                    .code(salesSubGoodsForSaleDto.getCode())
                    .vendorCode(salesSubGoodsForSaleDto.getVendorCode())
                    .transferred(salesSubGoodsForSaleDto.getTransferred())
                    .accepted(salesSubGoodsForSaleDto.getAccepted())
                    .amount(salesSubGoodsForSaleDto.getAmount())
                    .sum(salesSubGoodsForSaleDto.getSum())
                    .returned(salesSubGoodsForSaleDto.getReturned())
                    .remainder(salesSubGoodsForSaleDto.getRemainder())
                    .reportAmount(salesSubGoodsForSaleDto.getReportAmount())
                    .reportSum(salesSubGoodsForSaleDto.getReportSum())
                    .notReportAmount(salesSubGoodsForSaleDto.getNotReportAmount())
                    .notReportSum(salesSubGoodsForSaleDto.getNotReportSum())
                    .build();
        }
    }

    default SalesSubGoodsForSaleDto toDto(SalesSubGoodsForSale salesSubGoodsForSale) {
        if (salesSubGoodsForSale == null) {
            return null;
        } else {
            return SalesSubGoodsForSaleDto.builder()
                    .id(salesSubGoodsForSale.getId())
                    .productId(salesSubGoodsForSaleProductId(salesSubGoodsForSale))
                    .code(salesSubGoodsForSale.getCode())
                    .vendorCode(salesSubGoodsForSale.getVendorCode())
                    .transferred(salesSubGoodsForSale.getTransferred())
                    .accepted(salesSubGoodsForSale.getAccepted())
                    .amount(salesSubGoodsForSale.getAmount())
                    .sum(salesSubGoodsForSale.getSum())
                    .returned(salesSubGoodsForSale.getReturned())
                    .remainder(salesSubGoodsForSale.getRemainder())
                    .reportAmount(salesSubGoodsForSale.getReportAmount())
                    .reportSum(salesSubGoodsForSale.getReportSum())
                    .notReportAmount(salesSubGoodsForSale.getNotReportAmount())
                    .notReportSum(salesSubGoodsForSale.getNotReportSum())
                    .build();
        }
    }

    private Long salesSubGoodsForSaleProductId(SalesSubGoodsForSale salesSubGoodsForSale) {
        if (salesSubGoodsForSale == null) {
            return null;
        }
        Product product = salesSubGoodsForSale.getName();
        if (product == null) {
            return null;
        }
        Long id = product.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    default Product salesSubGoodsForSaleDtoToProduct(SalesSubGoodsForSaleDto salesSubGoodsForSaleDto) {
        if (salesSubGoodsForSaleDto == null) {
            return null;
        }
        Product product = new Product();

        product.setId(salesSubGoodsForSaleDto.getProductId());
        return product;
    }
}
