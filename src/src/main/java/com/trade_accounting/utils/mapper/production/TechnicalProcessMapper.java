package src.main.java.com.trade_accounting.utils.mapper.production;


import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.production.StagesProduction;
import com.trade_accounting.models.entity.production.TechnicalProcess;
import com.trade_accounting.models.dto.production.TechnicalProcessDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TechnicalProcessMapper {

    default TechnicalProcess toModel(TechnicalProcessDto dto) {
        if ( dto == null ) {
            return null;
        }

        TechnicalProcess.TechnicalProcessBuilder technicalProcess = TechnicalProcess.builder();

        technicalProcess.departmentOwner( technicalProcessDtoToDepartment( dto ) );
        technicalProcess.employeeOwner( technicalProcessDtoToEmployee( dto ) );
        technicalProcess.employeeWhoLastChanged( technicalProcessDtoToEmployee1( dto ) );
        if ( dto.getDateOfChanged() != null ) {
            technicalProcess.dateOfChanged( LocalDateTime.parse( dto.getDateOfChanged(), DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" ) ) );
        }
        technicalProcess.id( dto.getId() );
        technicalProcess.name( dto.getName() );
        technicalProcess.description( dto.getDescription() );
        technicalProcess.isArchived( dto.getIsArchived() );
        technicalProcess.isShared( dto.getIsShared() );
        technicalProcess.stagesProductionSet(technicalProcessDtoToStagesProductionSet(dto));

        return technicalProcess.build();
    }


    default TechnicalProcessDto toDto(TechnicalProcess process) {
        if ( process == null ) {
            return null;
        }

        TechnicalProcessDto.TechnicalProcessDtoBuilder technicalProcessDto = TechnicalProcessDto.builder();

        technicalProcessDto.departmentOwnerId( processDepartmentOwnerId( process ) );
        technicalProcessDto.employeeOwnerId( processEmployeeOwnerId( process ) );
        technicalProcessDto.employeeWhoLastChangedId( processEmployeeWhoLastChangedId( process ) );
        if ( process.getDateOfChanged() != null ) {
            technicalProcessDto.dateOfChanged( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" ).format( process.getDateOfChanged() ) );
        }
        technicalProcessDto.id( process.getId() );
        technicalProcessDto.name( process.getName() );
        technicalProcessDto.description( process.getDescription() );
        technicalProcessDto.isArchived( process.getIsArchived() );
        technicalProcessDto.isShared( process.getIsShared() );
        technicalProcessDto.stagesProductionIds(processStageProductionIds(process));

        return technicalProcessDto.build();
    }


     default void updateModelFromDto(TechnicalProcessDto dto, TechnicalProcess process) {
        if ( dto == null ) {
            return;
        }

        process.setId( dto.getId() );
        process.setName( dto.getName() );
        process.setDescription( dto.getDescription() );
        process.setIsArchived( dto.getIsArchived() );
        process.setIsShared( dto.getIsShared() );
        if ( dto.getDateOfChanged() != null ) {
            process.setDateOfChanged( LocalDateTime.parse( dto.getDateOfChanged() ) );
        }
        else {
            process.setDateOfChanged( null );
        }
    }

    private Set<StagesProduction> technicalProcessDtoToStagesProductionSet(TechnicalProcessDto technicalProcessDto) {
        if ( technicalProcessDto == null ) {
            return null;
        }

        Set<StagesProduction> stagesProductionSet = new HashSet<>();
        technicalProcessDto.getStagesProductionIds()
                .forEach(e -> {
                    StagesProduction stagesProduction = new StagesProduction();
                    stagesProduction.setId(e);
                    stagesProductionSet.add(stagesProduction);
                });

        return stagesProductionSet;
    }

    private Set<Long> processStageProductionIds(TechnicalProcess technicalProcess) {
        if ( technicalProcess == null ) {
            return null;
        }

        Set<Long> stageProductionIdsSet = new HashSet<>();
        technicalProcess.getStagesProductionSet()
                .forEach(e -> stageProductionIdsSet.add(e.getId()));

        return stageProductionIdsSet;
    }

    private Department technicalProcessDtoToDepartment(TechnicalProcessDto technicalProcessDto) {
        if ( technicalProcessDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( technicalProcessDto.getDepartmentOwnerId() );

        return department;
    }

    private Employee technicalProcessDtoToEmployee(TechnicalProcessDto technicalProcessDto) {
        if ( technicalProcessDto == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.id( technicalProcessDto.getEmployeeOwnerId() );

        return employee.build();
    }

    private Employee technicalProcessDtoToEmployee1(TechnicalProcessDto technicalProcessDto) {
        if ( technicalProcessDto == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.id( technicalProcessDto.getEmployeeWhoLastChangedId() );

        return employee.build();
    }

    private Long processDepartmentOwnerId(TechnicalProcess technicalProcess) {
        if ( technicalProcess == null ) {
            return null;
        }
        Department departmentOwner = technicalProcess.getDepartmentOwner();
        if ( departmentOwner == null ) {
            return null;
        }
        Long id = departmentOwner.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long processEmployeeOwnerId(TechnicalProcess technicalProcess) {
        if ( technicalProcess == null ) {
            return null;
        }
        Employee employeeOwner = technicalProcess.getEmployeeOwner();
        if ( employeeOwner == null ) {
            return null;
        }
        Long id = employeeOwner.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long processEmployeeWhoLastChangedId(TechnicalProcess technicalProcess) {
        if ( technicalProcess == null ) {
            return null;
        }
        Employee employeeWhoLastChanged = technicalProcess.getEmployeeWhoLastChanged();
        if ( employeeWhoLastChanged == null ) {
            return null;
        }
        Long id = employeeWhoLastChanged.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

}
