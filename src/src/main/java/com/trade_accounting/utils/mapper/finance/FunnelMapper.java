package src.main.java.com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.dto.finance.FunnelDto;
import com.trade_accounting.models.entity.finance.Funnel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FunnelMapper {

    default FunnelDto toDto(Funnel funnel) {
        if (funnel == null) {
            return null;
        }
        FunnelDto.FunnelDtoBuilder funnelDtoBuilder = FunnelDto.builder()
                .id(funnel.getId())
                .statusName(funnel.getStatusName())
                .count(funnel.getCount())
                .time(funnel.getTime())
                .conversion(funnel.getConversion())
                .price(funnel.getPrice())
                .type(funnel.getType());
        return funnelDtoBuilder.build();
    }

        default Funnel toModel(FunnelDto emp) {
        if (emp == null) {
            return null;
        }
        Funnel.FunnelBuilder funnelBuilder = Funnel.builder()
                .id(emp.getId())
                .statusName(emp.getStatusName())
                .time(emp.getTime())
                .count(emp.getCount())
                .conversion(emp.getConversion())
                .price(emp.getPrice())
                .type(emp.getType());

        return funnelBuilder.build();
    }
}
