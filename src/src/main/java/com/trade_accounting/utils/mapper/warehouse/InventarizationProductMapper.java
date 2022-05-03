package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.InventarizationProduct;
import com.trade_accounting.models.dto.warehouse.InventarizationProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventarizationProductMapper {
    /**
     * @return InventarizationProduct
     */
    @Mapping(source = "productId", target = "product.id")
    default InventarizationProduct toModel(InventarizationProductDto inventarizationProductDto) {
        if (inventarizationProductDto == null) {
            return null;
        }
        return InventarizationProduct.builder()
                .id(inventarizationProductDto.getId())
                .actualAmount(inventarizationProductDto.getActualAmount())
                .price(inventarizationProductDto.getPrice())
                .build();
    }

    /**
     * @return InventarizationProductDto
     */
    default InventarizationProductDto toDto(InventarizationProduct inventarizationProduct) {
        InventarizationProductDto internalOrderProductsDto = new InventarizationProductDto();
        if (inventarizationProduct == null) {
            return null;
        } else {
            internalOrderProductsDto.setId(inventarizationProduct.getId());
            internalOrderProductsDto.setProductId(inventarizationProduct.getProduct().getId());
            internalOrderProductsDto.setPrice(inventarizationProduct.getPrice());
            internalOrderProductsDto.setActualAmount(inventarizationProduct.getActualAmount());
            return internalOrderProductsDto;
        }
    }
}
