package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.AccessParameters;
import com.trade_accounting.models.dto.company.AccessParametersDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessParametersMapper {
    //AccessParameters
    default AccessParameters toModel(AccessParametersDto accessParametersDto) {
        if (accessParametersDto == null) {
            return null;
        }

        return AccessParameters.builder()
                .id(accessParametersDto.getId())
                .generalAccess(accessParametersDto.getGeneralAccess())
                .build();
    }

    default AccessParametersDto toDto(AccessParameters accessParameters) {
        AccessParametersDto accessParametersDto = new AccessParametersDto();
        if (accessParameters == null || accessParameters.getEmployee().getId() == null
                || accessParameters.getDepartment().getId() == null) {
            return null;
        } else {
            accessParametersDto.setId(accessParameters.getId());
            accessParametersDto.setGeneralAccess(accessParameters.getGeneralAccess());
            accessParametersDto.setEmployeeId(accessParameters.getEmployee().getId());
            accessParametersDto.setDepartmentId(accessParameters.getDepartment().getId());
            return accessParametersDto;
        }
    }
}
