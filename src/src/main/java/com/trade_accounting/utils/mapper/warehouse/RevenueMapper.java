package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Revenue;
import com.trade_accounting.models.dto.warehouse.RevenueDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface RevenueMapper {

    @Mappings({
            @Mapping(target = "product.id", source = "productId"),
            @Mapping(target = "product.description", source = "description"),
            @Mapping(target = "product.unit.id", source = "unitId"),
			@Mapping(target = "acceptanceProduction.id", source = "acceptanceProductionId"),
            @Mapping(target = "acceptanceProduction.amount", source = "amountAcceptance"),
			@Mapping(target = "acceptance.id", source = "acceptanceId"),
            @Mapping(target = "acceptance.date", source = "incomingNumberDate", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(target = "invoiceProduct.id", source = "invoiceProductId"),
            @Mapping(target = "invoiceProduct.amount", source = "amountShipment")
    })
	Revenue toModel(RevenueDto revenueDto);

	@Mappings({
			@Mapping(source = "product.id", target = "productId"),
			@Mapping(source = "product.description", target = "description"),
			@Mapping(source = "product.unit.id", target = "unitId"),
			@Mapping(source = "product.unit.shortName", target = "unitShortName"),
			@Mapping(source = "acceptanceProduction.id", target = "acceptanceProductionId"),
			@Mapping(source = "acceptanceProduction.amount", target = "amountAcceptance"),
			@Mapping(source = "acceptance.id", target = "acceptanceId"),
			@Mapping(source = "acceptance.date", target = "incomingNumberDate", dateFormat = "yyyy-MM-dd HH:mm"),
			@Mapping(source = "invoiceProduct.id", target = "invoiceProductId"),
			@Mapping(source = "invoiceProduct.amount", target = "amountShipment")
	})
	RevenueDto toDto(Revenue revenue);

}
