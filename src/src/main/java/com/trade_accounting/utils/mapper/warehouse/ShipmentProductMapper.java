package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.ShipmentProduct;
import com.trade_accounting.models.dto.warehouse.ShipmentProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentProductMapper {

    default ShipmentProduct toModel(ShipmentProductDto shipmentProductDto) {
        if ( shipmentProductDto == null ) {
            return null;
        }

        ShipmentProduct.ShipmentProductBuilder shipmentProduct = ShipmentProduct.builder();

        shipmentProduct.product( shipmentProductDtoToProduct( shipmentProductDto ) );
        shipmentProduct.id( shipmentProductDto.getId() );
        shipmentProduct.amount( shipmentProductDto.getAmount() );
        shipmentProduct.price( shipmentProductDto.getPrice() );

        return shipmentProduct.build();
    }


    default ShipmentProductDto toDto(ShipmentProduct shipmentProduct) {
        if ( shipmentProduct == null ) {
            return null;
        }

        ShipmentProductDto.ShipmentProductDtoBuilder shipmentProductDto = ShipmentProductDto.builder();

        shipmentProductDto.productId( shipmentProductProductId( shipmentProduct ) );
        shipmentProductDto.id( shipmentProduct.getId() );
        shipmentProductDto.amount( shipmentProduct.getAmount() );
        shipmentProductDto.price( shipmentProduct.getPrice() );

        return shipmentProductDto.build();
    }

    default Product shipmentProductDtoToProduct(ShipmentProductDto shipmentProductDto) {
        if ( shipmentProductDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( shipmentProductDto.getProductId() );

        return product.build();
    }

    default Long shipmentProductProductId(ShipmentProduct shipmentProduct) {
        if ( shipmentProduct == null ) {
            return null;
        }
        Product product = shipmentProduct.getProduct();
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
