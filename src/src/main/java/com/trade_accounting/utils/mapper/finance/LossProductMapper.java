package src.main.java.com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.LossProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.finance.LossProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LossProductMapper {

//    @Mapping(source = "productId", target = "product.id")
//    LossProduct toModel(LossProductDto lossProductDto);
//
//    @Mapping(source = "product.id", target = "productId")
//    LossProductDto toDto(LossProduct lossProduct);
//}
    default LossProduct toModel(LossProductDto lossProductDto) {
        if ( lossProductDto == null ) {
            return null;
        }

        LossProduct.LossProductBuilder lossProduct = LossProduct.builder();

        lossProduct.product( lossProductDtoToProduct( lossProductDto ) );
        lossProduct.id( lossProductDto.getId() );
        lossProduct.amount( lossProductDto.getAmount() );
        lossProduct.price( lossProductDto.getPrice() );

        return lossProduct.build();
    }


    default LossProductDto toDto(LossProduct lossProduct) {
        if ( lossProduct == null ) {
            return null;
        }

        LossProductDto.LossProductDtoBuilder lossProductDto = LossProductDto.builder();

        lossProductDto.productId( lossProductProductId( lossProduct ) );
        lossProductDto.id( lossProduct.getId() );
        lossProductDto.amount( lossProduct.getAmount() );
        lossProductDto.price( lossProduct.getPrice() );

        return lossProductDto.build();
    }

    default Product lossProductDtoToProduct(LossProductDto lossProductDto) {
        if ( lossProductDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( lossProductDto.getProductId() );

        return product.build();
    }

    default Long lossProductProductId(LossProduct lossProduct) {
        if ( lossProduct == null ) {
            return null;
        }
        Product product = lossProduct.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
