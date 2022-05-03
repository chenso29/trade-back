package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.SerialNumbersDto;
import com.trade_accounting.models.entity.warehouse.SerialNumbers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SerialNumbersMapper {

        @Mappings({
                @Mapping(target = "product.id", source = "productId"),
                @Mapping(target = "product.description", source = "description"),
                @Mapping(target = "warehouse.id", source = "warehouseId"),

        })
        SerialNumbers toModel(SerialNumbersDto serialNumbersDto);

        @Mappings({
                @Mapping(source = "product.id", target = "productId"),
                @Mapping(source = "product.description", target = "description"),
                @Mapping(source = "warehouse.id", target = "warehouseId"),
        })
        SerialNumbersDto toDto(SerialNumbers serialNumbers);

    }
