package src.main.java.com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    //Unit
    default Unit toModel(UnitDto unitDto) {
        if (unitDto == null) {
            return null;
        }

        return Unit.builder()
                .id(unitDto.getId())
                .fullName(unitDto.getFullName())
                .sortNumber(unitDto.getSortNumber())
                .shortName(unitDto.getShortName())
                .unitType(unitDto.getUnitType())
                .departmentOwner(unitDto.getDepartmentOwner())
                .employeeOwner(unitDto.getEmployeeOwner())
                .generalAccess(unitDto.isGeneralAccess())
                .employeeChange(unitDto.getEmployeeChange())
                .isRecyclebin(unitDto.getIsRecyclebin())
                .dateOfChange(LocalDateTime.parse(unitDto.getDateOfChange()))
                .build();
    }

    default UnitDto toDto(Unit unit) {
        UnitDto unitDto = new UnitDto();
        if (unit == null) {
            return null;
        } else {
            unitDto.setId(unit.getId());
            unitDto.setUnitType(unit.getUnitType());
            unitDto.setFullName(unit.getFullName());
            unitDto.setShortName(unit.getShortName());
            unitDto.setDepartmentOwner(unit.getDepartmentOwner());
            unitDto.setEmployeeOwner(unit.getEmployeeOwner());
            unitDto.setGeneralAccess(unit.isGeneralAccess());
            unitDto.setDateOfChange(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(unit.getDateOfChange()));
            unitDto.setEmployeeChange(unit.getEmployeeChange());
            unitDto.setSortNumber(unit.getSortNumber());
            unitDto.setIsRecyclebin(unit.getIsRecyclebin());
            return unitDto;
        }

    }
}
