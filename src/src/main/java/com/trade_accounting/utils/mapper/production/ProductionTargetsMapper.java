package src.main.java.com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.ProductionTargets;
import com.trade_accounting.models.dto.production.ProductionTargetsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ProductionTargetsMapper {

    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd HH:mm"),
                    @Mapping(source = "companyId", target = "company.id"),
                    @Mapping(source = "deliveryPlannedMoment", target = "deliveryPlannedMoment", dateFormat = "yyyy-MM-dd HH:mm"),
                    @Mapping(source = "materialWarehouse", target = "materialWarehouse.id"),
                    @Mapping(source = "productionWarehouse", target = "productionWarehouse.id"),
                    @Mapping(source = "productionStart", target = "productionStart", dateFormat = "yyyy-MM-dd HH:mm"),
                    @Mapping(source = "productionEnd", target = "productionEnd", dateFormat = "yyyy-MM-dd HH:mm"),
                    @Mapping(source = "shared", target = "shared"),
                    @Mapping(source = "owner", target = "Owner"),
                    @Mapping(source = "employeeOwner", target = "employeeOwner"),
                    @Mapping(source = "published", target = "published"),
                    @Mapping(source = "printed", target = "printed"),
                    @Mapping(source = "description", target = "description"),
                    @Mapping(source = "updated", target = "updated", dateFormat = "yyyy-MM-dd HH:mm"),
                    @Mapping(source = "updatedByName", target = "updatedByName"),
            }
    )

     ProductionTargets toModel(ProductionTargetsDto productionTargetsDto);

    default ProductionTargetsDto toDto(ProductionTargets productionTargets) {

        ProductionTargetsDto productionTargetsDto = new ProductionTargetsDto();

        if (productionTargets == null) {
            return null;
        }

        productionTargetsDto.setId(productionTargets.getId());
        productionTargetsDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(productionTargets.getDate()));
        productionTargetsDto.setCompanyId(productionTargets.getCompany().getId());
        productionTargetsDto.setDeliveryPlannedMoment(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(productionTargets.getDeliveryPlannedMoment()));
        productionTargetsDto.setMaterialWarehouse(productionTargets.getMaterialWarehouse().getId());
        productionTargetsDto.setProductionWarehouse(productionTargets.getProductionWarehouse().getId());
        productionTargetsDto.setProductionStart(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(productionTargets.getProductionStart()));
        productionTargetsDto.setProductionEnd(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(productionTargets.getProductionEnd()));
        productionTargetsDto.setShared(productionTargets.getShared());
        productionTargetsDto.setOwner(productionTargets.getOwner());
        productionTargetsDto.setEmployeeOwner(productionTargets.getEmployeeOwner());
        productionTargetsDto.setPublished(productionTargets.getPublished());
        productionTargetsDto.setPrinted(productionTargets.getPrinted());
        productionTargetsDto.setDescription(productionTargets.getDescription());
        productionTargetsDto.setUpdated(productionTargets.getUpdated().toString());
        productionTargetsDto.setUpdatedByName(productionTargets.getUpdatedByName());

        return productionTargetsDto;

    }

}
