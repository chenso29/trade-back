package src.main.java.com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.OrdersOfProduction;
import com.trade_accounting.models.dto.production.OrdersOfProductionDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface OrdersOfProductionMapper {

    default OrdersOfProduction toModel(OrdersOfProductionDto ordersOfProductionDto) {
        if (ordersOfProductionDto == null) {
            return null;
        }
        return OrdersOfProduction.builder()
                .id(ordersOfProductionDto.getId())
                .produce(ordersOfProductionDto.getProduce())
                .volume(ordersOfProductionDto.getVolume())
                .build();
    }

    default OrdersOfProductionDto toDto(OrdersOfProduction ordersOfProduction) {
        OrdersOfProductionDto ordersOfProductionDto = new OrdersOfProductionDto();
        if (ordersOfProduction == null) {
            return null;
        }

        ordersOfProductionDto.setId(ordersOfProduction.getId());
        ordersOfProductionDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(ordersOfProduction.getDate()));
        ordersOfProductionDto.setCompanyId(ordersOfProduction.getCompany().getId());
        ordersOfProductionDto.setTechnicalCardId(ordersOfProduction.getCompany().getId());
        ordersOfProductionDto.setVolume(ordersOfProduction.getVolume());
        ordersOfProductionDto.setProduce(ordersOfProduction.getProduce());
        ordersOfProductionDto.setPlannedProductionDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(ordersOfProduction.getPlannedProductionDate()));
        ordersOfProductionDto.setIsSent(ordersOfProduction.getIsSent());
        ordersOfProductionDto.setIsPrint(ordersOfProduction.getIsPrint());
        ordersOfProductionDto.setComment(ordersOfProduction.getComment());

        return ordersOfProductionDto;
    }
}

