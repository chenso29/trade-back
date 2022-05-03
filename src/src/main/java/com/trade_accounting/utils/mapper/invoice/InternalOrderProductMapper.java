package src.main.java.com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import com.trade_accounting.models.dto.invoice.InternalOrderProductsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InternalOrderProductMapper {
    /**
     * @return InternalOrderProducts
     */
    @Mapping(source = "productId", target = "product.id")
    default InternalOrderProduct toModel(InternalOrderProductsDto internalOrderProductsDto) {
        if (internalOrderProductsDto == null) {
            return null;
        }
        return InternalOrderProduct.builder()
                .id(internalOrderProductsDto.getId())
                .amount(internalOrderProductsDto.getAmount())
                .price(internalOrderProductsDto.getPrice())
                .build();
    }

    /**
     * @return InternalOrderProductsDto
     */
    default InternalOrderProductsDto toDto(InternalOrderProduct internalOrderProduct) {
        InternalOrderProductsDto internalOrderProductsDto = new InternalOrderProductsDto();
        if (internalOrderProduct == null) {
            return null;
        } else {
            internalOrderProductsDto.setId(internalOrderProduct.getId());
            internalOrderProductsDto.setProductId(internalOrderProduct.getProduct().getId());
            internalOrderProductsDto.setPrice(internalOrderProduct.getPrice());
            internalOrderProductsDto.setAmount(internalOrderProduct.getAmount());
            return internalOrderProductsDto;
        }
    }
}