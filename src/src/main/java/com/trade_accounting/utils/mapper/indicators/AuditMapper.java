package src.main.java.com.trade_accounting.utils.mapper.indicators;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.indicators.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    @Mappings({
            @Mapping(source = "employeeId", target = "employee.id"),
            @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    })
    Audit auditDtoToAudit(AuditDto auditDto);

    @Mappings({
            @Mapping(source = "employee.id", target = "employeeId"),
            @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    })
    AuditDto auditToAuditDto(Audit audit);
}
