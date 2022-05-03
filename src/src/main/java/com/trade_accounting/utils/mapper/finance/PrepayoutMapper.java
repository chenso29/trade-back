package src.main.java.com.trade_accounting.utils.mapper.finance;


import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.finance.Prepayout;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.finance.PrepayoutDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PrepayoutMapper {

    default PrepayoutDto toDto(Prepayout prepayout){
        if( prepayout == null){
            return null;
        }
        PrepayoutDto.PrepayoutDtoBuilder prepayoutDto = PrepayoutDto.builder();

        prepayoutDto.id( prepayout.getId() );
        if ( prepayout.getDate() != null ) {
            prepayoutDto.date( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( prepayout.getDate() ) );
        }
        prepayoutDto.retailStoreId(prepayoutRetailStoreId(prepayout));
        prepayoutDto.contractorId(buyersReturnContractorId(prepayout));
        prepayoutDto.companyId(buyersReturnCompanyId(prepayout));
        prepayoutDto.cash(prepayout.getCash());
        prepayoutDto.cashless(prepayout.getCashless());
        prepayoutDto.discount(prepayout.getDiscount());
        prepayoutDto.sum(prepayout.getSum());
        prepayoutDto.isSent(prepayout.getIsSent());
        prepayoutDto.isPrint(prepayout.getIsPrint());
        prepayoutDto.comment(prepayout.getComment());

        return prepayoutDto.build();
    }

    default Prepayout toModel(PrepayoutDto emp){
        if( emp == null){
            return null;
        }
        Prepayout.PrepayoutBuilder prepayout = Prepayout.builder();

        prepayout.id(emp.getId());
        if (emp.getDate() != null ) {
            prepayout.date(LocalDateTime.parse(emp.getDate()));
        }
        prepayout.retailStore(prepayoutDtoToRetailStore(emp));
        prepayout.contractor(prepayoutDtoToContractor(emp));
        prepayout.company(prepayoutDtoToCompany(emp));
        prepayout.cash(emp.getCash());
        prepayout.cashless(emp.getCashless());
        prepayout.discount(emp.getDiscount());
        prepayout.sum(emp.getSum());
        prepayout.isSent(emp.getIsSent());
        prepayout.isPrint(emp.getIsPrint());
        prepayout.comment(emp.getComment());

        return prepayout.build();
    }



    private Long buyersReturnCompanyId(Prepayout shipment) {
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

    private Long buyersReturnContractorId(Prepayout buyersReturn) {
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

    private Long prepayoutRetailStoreId(Prepayout prepayout) {
        if ( prepayout == null ) {
            return null;
        }
        RetailStore retailStore = prepayout.getRetailStore();
        if ( retailStore == null ) {
            return null;
        }
        Long id = retailStore.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Company prepayoutDtoToCompany(PrepayoutDto prepayoutDto) {
        if ( prepayoutDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( prepayoutDto.getCompanyId() );

        return company;
    }

    default Contractor prepayoutDtoToContractor(PrepayoutDto prepayoutDto) {
        if ( prepayoutDto == null ) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId( prepayoutDto.getContractorId() );

        return contractor;
    }

    default RetailStore prepayoutDtoToRetailStore(PrepayoutDto prepayoutDto) {
        if ( prepayoutDto == null ) {
            return null;
        }

        RetailStore.RetailStoreBuilder retailStore = RetailStore.builder();

        retailStore.id( prepayoutDto.getRetailStoreId() );

        return retailStore.build();
    }
}
