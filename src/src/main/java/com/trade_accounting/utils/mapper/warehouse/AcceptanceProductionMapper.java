package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import com.trade_accounting.models.dto.warehouse.AcceptanceProductionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AcceptanceProductionMapper {

    @Mappings({
            @Mapping(source = "acceptance.id", target = "acceptanceId"),
            @Mapping(source = "product.id", target = "productId")
    })
    AcceptanceProductionDto toDto(AcceptanceProduction acceptanceProduction);

    @Mappings({
            @Mapping(source = "acceptanceId", target = "acceptance.id"),
            @Mapping(source = "productId", target = "product.id")
    })    AcceptanceProduction toModel(AcceptanceProductionDto acceptanceProductionDto);
}
