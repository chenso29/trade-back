package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface BuyersReturnMapper {

    default BuyersReturnDto toDto(BuyersReturn buyersReturn){
        if( buyersReturn == null){
            return null;
        }
        BuyersReturnDto.BuyersReturnDtoBuilder buyersReturnDto = BuyersReturnDto.builder();

        buyersReturnDto.id( buyersReturn.getId() );
        if ( buyersReturn.getDate() != null ) {
            buyersReturnDto.date( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( buyersReturn.getDate() ) );
        }
        buyersReturnDto.companyId( buyersReturnCompanyId( buyersReturn ) );
        buyersReturnDto.contractorId( buyersReturnContractorId( buyersReturn ) );
        buyersReturnDto.warehouseId( buyersReturnWarehouseId( buyersReturn ) );
        buyersReturnDto.sum(buyersReturn.getSum());
        buyersReturnDto.isSent(buyersReturn.getIsSent());
        buyersReturnDto.isPrint(buyersReturn.getIsPrint());
        buyersReturnDto.comment( buyersReturn.getComment() );

        return buyersReturnDto.build();
    }

    default BuyersReturn toModel(BuyersReturnDto emp){
        if( emp == null){
            return null;
        }
        BuyersReturn.BuyersReturnBuilder buyersReturn = BuyersReturn.builder();

        buyersReturn.id(emp.getId());
        if (emp.getDate() != null ) {
            buyersReturn.date(LocalDateTime.parse(emp.getDate()));
        }
        buyersReturn.company(buyersReturnDtoToCompany(emp));
        buyersReturn.contractor(buyersReturnDtoToContractor(emp));
        buyersReturn.warehouse(buyersReturnDtoToWarehouse(emp));
        buyersReturn.sum(emp.getSum());
        buyersReturn.isSent(emp.getIsSent());
        buyersReturn.isPrint(emp.getIsPrint());
        buyersReturn.comment(emp.getComment());

        return buyersReturn.build();
    }



    private Long buyersReturnCompanyId(BuyersReturn shipment) {
        if ( shipment == null ) {
            return null;
        }
        Company company = shipment.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long buyersReturnContractorId(BuyersReturn buyersReturn) {
        if ( buyersReturn == null ) {
            return null;
        }
        Contractor contractor = buyersReturn.getContractor();
        if ( contractor == null ) {
            return null;
        }
        Long id = contractor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long buyersReturnWarehouseId(BuyersReturn buyersReturn) {
        if ( buyersReturn == null ) {
            return null;
        }
        Warehouse warehouse = buyersReturn.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        Long id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Company buyersReturnDtoToCompany(BuyersReturnDto buyersReturnDto) {
        if ( buyersReturnDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( buyersReturnDto.getCompanyId() );

        return company;
    }

    default Contractor buyersReturnDtoToContractor(BuyersReturnDto buyersReturnDto) {
        if ( buyersReturnDto == null ) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId( buyersReturnDto.getContractorId() );

        return contractor;
    }

    default Warehouse buyersReturnDtoToWarehouse(BuyersReturnDto shipmentDto) {
        if ( shipmentDto == null ) {
            return null;
        }

        Warehouse.WarehouseBuilder warehouse = Warehouse.builder();

        warehouse.id( shipmentDto.getWarehouseId() );

        return warehouse.build();
    }

}
