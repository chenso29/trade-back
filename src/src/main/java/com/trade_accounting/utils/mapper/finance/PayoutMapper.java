package src.main.java.com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.Payout;
import com.trade_accounting.models.dto.finance.PayoutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PayoutMapper {
    //Payout
    @Mappings({
            @Mapping(source = "retailStore.id", target = "retailStoreId"),
            @Mapping(source = "company.id", target = "companyId")
    })
    PayoutDto toDto(Payout payout);

    @Mappings({
            @Mapping(source = "retailStoreId", target = "retailStore.id"),
            @Mapping(source = "companyId", target = "company.id")
    })
    Payout toModel(PayoutDto payoutsDto);

}

