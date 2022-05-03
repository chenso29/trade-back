package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    //Warehouse

    default WarehouseDto toDto(Warehouse warehouse) {
        if (warehouse == null) {
            return null;
        } else {
            return WarehouseDto.builder()
                    .id(warehouse.getId())
                    .name(warehouse.getName())
                    .sortNumber(warehouse.getSortNumber())
                    .address(warehouse.getAddress())
                    .commentToAddress(warehouse.getCommentToAddress())
                    .comment(warehouse.getComment())
                    .build();
        }
    }

    default Warehouse toModel(WarehouseDto warehouseDto) {
        if (warehouseDto == null) {
            return null;
        } else {
            return Warehouse.builder()
                    .id(warehouseDto.getId())
                    .name(warehouseDto.getName())
                    .sortNumber(warehouseDto.getSortNumber())
                    .address(warehouseDto.getAddress())
                    .commentToAddress(warehouseDto.getCommentToAddress())
                    .comment(warehouseDto.getComment())
                    .build();
        }
    }
}
