package src.main.java.com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.finance.PrepaymentReturnDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrepaymentReturnMapper {

     default PrepaymentReturn toModel(PrepaymentReturnDto prepaymentReturnDto) {
        if ( prepaymentReturnDto == null ) {
            return null;
        }

        PrepaymentReturn.PrepaymentReturnBuilder prepaymentReturn = PrepaymentReturn.builder();

        prepaymentReturn.company( prepaymentReturnDtoToCompany( prepaymentReturnDto ) );
        prepaymentReturn.contractor( prepaymentReturnDtoToContractor( prepaymentReturnDto ) );
        prepaymentReturn.retailStore( prepaymentReturnDtoToRetailStore( prepaymentReturnDto ) );
        prepaymentReturn.id( prepaymentReturnDto.getId() );
        prepaymentReturn.time( prepaymentReturnDto.getTime() );
        prepaymentReturn.sumCash( prepaymentReturnDto.getSumCash() );
        prepaymentReturn.sumNonСash( prepaymentReturnDto.getSumNonСash() );
        prepaymentReturn.sent( prepaymentReturnDto.isSent() );
        prepaymentReturn.printed( prepaymentReturnDto.isPrinted() );
        prepaymentReturn.comment( prepaymentReturnDto.getComment() );

        return prepaymentReturn.build();
    }

    default PrepaymentReturnDto toDto(PrepaymentReturn prepaymentReturn) {
        if ( prepaymentReturn == null ) {
            return null;
        }

        PrepaymentReturnDto.PrepaymentReturnDtoBuilder prepaymentReturnDto = PrepaymentReturnDto.builder();

        prepaymentReturnDto.companyId( prepaymentReturnCompanyId( prepaymentReturn ) );
        prepaymentReturnDto.contractorId( prepaymentReturnContractorId( prepaymentReturn ) );
        prepaymentReturnDto.retailStoreId( prepaymentReturnRetailStoreId( prepaymentReturn ) );
        prepaymentReturnDto.time( prepaymentReturn.getTime() );
        prepaymentReturnDto.id( prepaymentReturn.getId() );
        prepaymentReturnDto.sumCash( prepaymentReturn.getSumCash() );
        prepaymentReturnDto.sumNonСash( prepaymentReturn.getSumNonСash() );
        prepaymentReturnDto.sent( prepaymentReturn.isSent() );
        prepaymentReturnDto.printed( prepaymentReturn.isPrinted() );
        prepaymentReturnDto.comment( prepaymentReturn.getComment() );

        return prepaymentReturnDto.build();
    }

    default Company prepaymentReturnDtoToCompany(PrepaymentReturnDto prepaymentReturnDto) {
        if ( prepaymentReturnDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( prepaymentReturnDto.getCompanyId() );

        return company;
    }

    default Contractor prepaymentReturnDtoToContractor(PrepaymentReturnDto prepaymentReturnDto) {
        if ( prepaymentReturnDto == null ) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId( prepaymentReturnDto.getContractorId() );

        return contractor;
    }

    default RetailStore prepaymentReturnDtoToRetailStore(PrepaymentReturnDto prepaymentReturnDto) {
        if ( prepaymentReturnDto == null ) {
            return null;
        }

        RetailStore.RetailStoreBuilder retailStore = RetailStore.builder();

        retailStore.id( prepaymentReturnDto.getRetailStoreId() );

        return retailStore.build();
    }

    default Long prepaymentReturnCompanyId(PrepaymentReturn prepaymentReturn) {
        if ( prepaymentReturn == null ) {
            return null;
        }
        Company company = prepaymentReturn.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Long prepaymentReturnContractorId(PrepaymentReturn prepaymentReturn) {
        if ( prepaymentReturn == null ) {
            return null;
        }
        Contractor contractor = prepaymentReturn.getContractor();
        if ( contractor == null ) {
            return null;
        }
        Long id = contractor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long prepaymentReturnRetailStoreId(PrepaymentReturn prepaymentReturn) {
        if ( prepaymentReturn == null ) {
            return null;
        }
        RetailStore retailStore = prepaymentReturn.getRetailStore();
        if ( retailStore == null ) {
            return null;
        }
        Long id = retailStore.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
