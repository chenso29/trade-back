package src.main.java.com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.ReturnFromBuyers;
import com.trade_accounting.models.dto.finance.ReturnFromBuyersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReturnFromBuyersMapper {
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contract.id", target = "contractId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "warehouse.id", target = "warehouseId")
    })
    ReturnFromBuyersDto toDto(ReturnFromBuyers returnFromBuyers);

    @Mappings({
            @Mapping(source = "warehouseId", target = "warehouse.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractId", target = "contract.id")
    })
    ReturnFromBuyers toModel(ReturnFromBuyersDto returnFromBuyersDto);
}
