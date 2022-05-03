package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.ProductGroup;
import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductGroupMapper {
    //ProductGroup
    //На момент написания не известно работает или нет из-за рекурсии
    @Mappings({
            @Mapping(source = "productGroup.id", target = "parentId")
    })
    ProductGroupDto toDto(ProductGroup productGroup);

    @Mappings({
            @Mapping(source = "parentId", target = "productGroup.id")
    })
    ProductGroup toModel(ProductGroupDto productGroupDto);

}
