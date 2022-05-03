package src.main.java.com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.TechnicalCardProduction;
import com.trade_accounting.models.dto.production.TechnicalCardProductionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TechnicalCardProductionMapper {
    //TechnicalCardProduction
    @Mappings({
            @Mapping(source = "product.id", target = "productId"),
    })
    TechnicalCardProductionDto toDto(TechnicalCardProduction technicalCardProduction);

    @Mappings({
            @Mapping(target = "product", ignore = true)
    })
    TechnicalCardProduction toModel(TechnicalCardProductionDto technicalCardProductionDto);

}
