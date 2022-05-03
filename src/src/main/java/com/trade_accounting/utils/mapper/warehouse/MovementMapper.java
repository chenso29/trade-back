package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.warehouse.Movement;
import com.trade_accounting.models.entity.warehouse.MovementProduct;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovementMapper{
    //    Movement
    default MovementDto toDto(Movement movement) {
        MovementDto movementDto = new MovementDto();
        if (movement == null) {
            return null;
        } else {
            movementDto.setId(movement.getId());
            movementDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(movement.getDate()));
            movementDto.setIsSent(movement.getIsSent());
            movementDto.setIsPrint(movement.getIsPrint());
            movementDto.setComment(movement.getComment());
            movementDto.setIsRecyclebin(movement.getIsRecyclebin());
            BigDecimal sum = movement.getMovementProducts().stream()
                    .map(x -> x.getAmount().multiply(x.getPrice()))
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

            movementDto.setSum(sum);
            movementDto.setEmployeeChangedId(movement.getEmployeeChanged().getId());
            Warehouse warehouseFrom = movement.getWarehouse();
            Warehouse warehouseTo = movement.getWarehouseTo();
            if (warehouseFrom == null) {
                return null;
            } else {
                movementDto.setWarehouseId(warehouseFrom.getId());
                if (warehouseTo == null){
                    return null;
                } else {
                    movementDto.setWarehouseToId(warehouseTo.getId());

                    Company company = movement.getCompany();
                    if (company == null){
                        return null;
                    } else {
                        movementDto.setCompanyId(company.getId());

                        List<Long> movementProductIds = movement.getMovementProducts().stream()
                                .map(MovementProduct::getId)
                                .collect(Collectors.toList());

                        movementDto.setMovementProductsIds(movementProductIds);
                        return movementDto;
                    }
                }
            }
        }
    }

    @Mapping(target = "date", ignore = true)
    Movement toModel(MovementDto movementDto);
}
