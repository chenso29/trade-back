package src.main.java.com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.Production;
import com.trade_accounting.models.dto.production.ProductionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductionMapper {
    //Production

    @Mappings({
            @Mapping(source = "technicalCard.id", target = "technicalCardId"),
            @Mapping(source = "requestsProductions.id", target = "requestsProductionsId")
    })
    ProductionDto toDto(Production production);

    @Mappings({
            @Mapping(target = "technicalCard.id", ignore = true),
            @Mapping(target = "requestsProductions.id", ignore = true)
    })
    Production toModel(ProductionDto productionDto);
}
