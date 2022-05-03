package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.MovementProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.warehouse.MovementProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementProductMapper {
    //    MovementProduct

    @Mapping(source = "product.id", target = "productId")
    default MovementProductDto toDto(MovementProduct movementProduct) {
       MovementProductDto movementProductDto = new MovementProductDto();
        if (movementProduct == null) {
            return null;
        } else {
            movementProductDto.setId(movementProduct.getId());
            movementProductDto.setProductId( movementProductProductId( movementProduct ) );
            movementProductDto.setPrice(movementProduct.getPrice());
            movementProductDto.setAmount(movementProduct.getAmount());
            return movementProductDto;
        }
    }

    default MovementProduct toModel(MovementProductDto movementProductDto) {
        if (movementProductDto == null) {
            return null;
        }
        MovementProduct.MovementProductBuilder movementProduct = MovementProduct.builder();

        movementProduct.product( movementProductDtoToProduct( movementProductDto ) );
        movementProduct.id( movementProductDto.getId() );
        movementProduct.amount( movementProductDto.getAmount() );
        movementProduct.price( movementProductDto.getPrice() );
        return movementProduct.build();
    }

    default Product movementProductDtoToProduct(MovementProductDto movementProductDto) {
        if ( movementProductDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id(movementProductDto.getProductId() );

        return product.build();
    }

    default Long movementProductProductId(MovementProduct movementProduct) {
        if ( movementProduct == null ) {
            return null;
        }
        Product product = movementProduct.getProduct();
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
