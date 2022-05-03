package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.util.Project;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.AcceptanceDto;
import com.trade_accounting.models.dto.warehouse.AcceptanceProductionDto;
import org.mapstruct.Mapper;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AcceptanceMapper {

    default AcceptanceDto toDto(Acceptance acceptance) {
        if ( acceptance == null ) {
            return null;
        }

        AcceptanceDto.AcceptanceDtoBuilder acceptanceDto = AcceptanceDto.builder();

        acceptanceDto.contractorId( acceptanceContractorId( acceptance ) );
        acceptanceDto.warehouseId( acceptanceWarehouseId( acceptance ) );
        acceptanceDto.contractId( acceptanceContractId( acceptance ) );
        acceptanceDto.companyId( acceptanceCompanyId( acceptance ) );
        acceptanceDto.projectId( acceptanceProjectId( acceptance ) );
        acceptanceDto.employeeChangedId( acceptanceEmployeeChangedId( acceptance ) );
        acceptanceDto.id( acceptance.getId() );
        acceptanceDto.incomingNumber( acceptance.getIncomingNumber() );
        acceptanceDto.comment( acceptance.getComment() );
        if ( acceptance.getDate() != null ) {
            acceptanceDto.date( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( acceptance.getDate() ) );
        }
        if ( acceptance.getWhenChangedDate() != null ) {
            acceptanceDto.whenChangedDate( DateTimeFormatter.ISO_LOCAL_DATE.format( acceptance.getWhenChangedDate() ) );
        }
        acceptanceDto.isSent( acceptance.getIsSent() );
        acceptanceDto.isPrint( acceptance.getIsPrint() );
        acceptanceDto.isSpend( acceptance.getIsSpend() );
        acceptanceDto.acceptanceProduction( acceptanceProductionListToAcceptanceProductionDtoList(acceptance.getAcceptanceProduction() ) );
        acceptanceDto.isRecyclebin( acceptance.getIsRecyclebin() );

        return acceptanceDto.build();
    }


    default Acceptance toModel(AcceptanceDto acceptance) {
        if ( acceptance == null ) {
            return null;
        }

        Acceptance.AcceptanceBuilder<?, ?> acceptance1 = Acceptance.builder();

        acceptance1.id( acceptance.getId() );
        if ( acceptance.getDate() != null ) {
            acceptance1.date( LocalDateTime.parse( acceptance.getDate() ) );
        }
        acceptance1.isSent( acceptance.getIsSent() );
        acceptance1.isPrint( acceptance.getIsPrint() );
        acceptance1.comment( acceptance.getComment() );
        acceptance1.isRecyclebin( acceptance.getIsRecyclebin() );
        acceptance1.incomingNumber( acceptance.getIncomingNumber() );
        acceptance1.isSpend( acceptance.getIsSpend() );
        if ( acceptance.getWhenChangedDate() != null ) {
            acceptance1.whenChangedDate( LocalDate.parse( acceptance.getWhenChangedDate() ) );
        }
        acceptance1.acceptanceProduction( acceptanceProductionDtoListToAcceptanceProductionList( acceptance.getAcceptanceProduction() ) );
        return acceptance1.build();
    }

    default Long acceptanceContractorId(Acceptance acceptance) {
        if ( acceptance == null ) {
            return null;
        }
        Contractor contractor = acceptance.getContractor();
        if ( contractor == null ) {
            return null;
        }
        Long id = contractor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Long acceptanceWarehouseId(Acceptance acceptance) {
        if ( acceptance == null ) {
            return null;
        }
        Warehouse warehouse = acceptance.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        Long id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Long acceptanceContractId(Acceptance acceptance) {
        if ( acceptance == null ) {
            return null;
        }
        Contract contract = acceptance.getContract();
        if ( contract == null ) {
            return null;
        }
        Long id = contract.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Long acceptanceCompanyId(Acceptance acceptance) {
        if ( acceptance == null ) {
            return null;
        }
        Company company = acceptance.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Long acceptanceProjectId(Acceptance acceptance) {
        if ( acceptance == null ) {
            return null;
        }
        Project project = acceptance.getProject();
        if ( project == null ) {
            return null;
        }
        Long id = project.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Long acceptanceEmployeeChangedId(Acceptance acceptance) {
        if ( acceptance == null ) {
            return null;
        }
        Employee employeeChanged = acceptance.getEmployeeChanged();
        if ( employeeChanged == null ) {
            return null;
        }
        Long id = employeeChanged.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default AcceptanceProductionDto acceptanceProductionToAcceptanceProductionDto(AcceptanceProduction acceptanceProduction) {
        if ( acceptanceProduction == null ) {
            return null;
        }

        AcceptanceProductionDto.AcceptanceProductionDtoBuilder acceptanceProductionDto = AcceptanceProductionDto.builder();

        acceptanceProductionDto.id( acceptanceProduction.getId() );
        acceptanceProductionDto.amount( acceptanceProduction.getAmount() );
        acceptanceProductionDto.price( acceptanceProduction.getPrice() );

        return acceptanceProductionDto.build();
    }

    default
    List<AcceptanceProductionDto> acceptanceProductionListToAcceptanceProductionDtoList(List<AcceptanceProduction> list) {
        if ( list == null ) {
            return null;
        }

        List<AcceptanceProductionDto> list1 = new ArrayList<AcceptanceProductionDto>( list.size() );
        for ( AcceptanceProduction acceptanceProduction : list ) {
            list1.add( acceptanceProductionToAcceptanceProductionDto( acceptanceProduction ) );
        }

        return list1;
    }

    default AcceptanceProduction acceptanceProductionDtoToAcceptanceProduction(AcceptanceProductionDto acceptanceProductionDto) {
        if ( acceptanceProductionDto == null ) {
            return null;
        }

        AcceptanceProduction.AcceptanceProductionBuilder acceptanceProduction = AcceptanceProduction.builder();

        acceptanceProduction.id( acceptanceProductionDto.getId() );
        acceptanceProduction.price( acceptanceProductionDto.getPrice() );
        acceptanceProduction.amount( acceptanceProductionDto.getAmount() );

        return acceptanceProduction.build();
    }

    default List<AcceptanceProduction> acceptanceProductionDtoListToAcceptanceProductionList(List<AcceptanceProductionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<AcceptanceProduction> list1 = new ArrayList<AcceptanceProduction>( list.size() );
        for ( AcceptanceProductionDto acceptanceProductionDto : list ) {
            list1.add( acceptanceProductionDtoToAcceptanceProduction( acceptanceProductionDto ) );
        }

        return list1;
    }
}