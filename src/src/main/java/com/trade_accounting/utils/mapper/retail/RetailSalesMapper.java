package src.main.java.com.trade_accounting.utils.mapper.retail;


import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.retail.RetailSales;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.retail.RetailSalesDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailSalesMapper {

//    @Mappings({
//            @Mapping(source = "company.id", target = "companyId"),
//            @Mapping(source = "contractor.id", target = "contractorId"),
//            @Mapping(source = "retailStore.id", target = "retailStoreId")
//    })
//    RetailSalesDto toDto(RetailSales retailSales);
//
//    @Mappings({
//            @Mapping(source = "companyId", target = "company.id"),
//            @Mapping(source = "contractorId", target = "contractor.id"),
//            @Mapping(source = "retailStoreId", target = "retailStore.id")
//
//    })
//    RetailSales toModel(RetailSalesDto retailSalesDto);


    default RetailSalesDto toDto(RetailSales retailSales) {
        if ( retailSales == null ) {
            return null;
        }

        RetailSalesDto.RetailSalesDtoBuilder retailSalesDto = RetailSalesDto.builder();

        retailSalesDto.companyId( retailSalesCompanyId( retailSales ) );
        retailSalesDto.contractorId( retailSalesContractorId( retailSales ) );
        retailSalesDto.retailStoreId( retailSalesRetailStoreId( retailSales ) );
        retailSalesDto.id( retailSales.getId() );
        retailSalesDto.time( retailSales.getTime() );
        retailSalesDto.sumCash( retailSales.getSumCash() );
        retailSalesDto.sumNonСash( retailSales.getSumNonСash() );
        retailSalesDto.prepayment( retailSales.getPrepayment() );
        retailSalesDto.sumDiscount( retailSales.getSumDiscount() );
        retailSalesDto.sum( retailSales.getSum() );
        retailSalesDto.sent( retailSales.isSent() );
        retailSalesDto.printed( retailSales.isPrinted() );
        retailSalesDto.comment( retailSales.getComment() );

        return retailSalesDto.build();
    }


    default RetailSales toModel(RetailSalesDto retailSalesDto) {
        if ( retailSalesDto == null ) {
            return null;
        }

        RetailSales.RetailSalesBuilder retailSales = RetailSales.builder();

        retailSales.company( retailSalesDtoToCompany( retailSalesDto ) );
        retailSales.contractor( retailSalesDtoToContractor( retailSalesDto ) );
        retailSales.retailStore( retailSalesDtoToRetailStore( retailSalesDto ) );
        retailSales.id( retailSalesDto.getId() );
        retailSales.time( retailSalesDto.getTime() );
        retailSales.sumCash( retailSalesDto.getSumCash() );
        retailSales.sumNonСash( retailSalesDto.getSumNonСash() );
        retailSales.prepayment( retailSalesDto.getPrepayment() );
        retailSales.sumDiscount( retailSalesDto.getSumDiscount() );
        retailSales.sum( retailSalesDto.getSum() );
        retailSales.sent( retailSalesDto.isSent() );
        retailSales.printed( retailSalesDto.isPrinted() );
        retailSales.comment( retailSalesDto.getComment() );

        return retailSales.build();
    }

    private Long retailSalesCompanyId(RetailSales retailSales) {
        if ( retailSales == null ) {
            return null;
        }
        Company company = retailSales.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long retailSalesContractorId(RetailSales retailSales) {
        if ( retailSales == null ) {
            return null;
        }
        Contractor contractor = retailSales.getContractor();
        if ( contractor == null ) {
            return null;
        }
        Long id = contractor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long retailSalesRetailStoreId(RetailSales retailSales) {
        if ( retailSales == null ) {
            return null;
        }
        RetailStore retailStore = retailSales.getRetailStore();
        if ( retailStore == null ) {
            return null;
        }
        Long id = retailStore.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Company retailSalesDtoToCompany(RetailSalesDto retailSalesDto) {
        if ( retailSalesDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( retailSalesDto.getCompanyId() );

        return company;
    }

    private Contractor retailSalesDtoToContractor(RetailSalesDto retailSalesDto) {
        if ( retailSalesDto == null ) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId( retailSalesDto.getContractorId() );

        return contractor;
    }

    private RetailStore retailSalesDtoToRetailStore(RetailSalesDto retailSalesDto) {
        if ( retailSalesDto == null ) {
            return null;
        }

        RetailStore.RetailStoreBuilder retailStore = RetailStore.builder();

        retailStore.id( retailSalesDto.getRetailStoreId() );

        return retailStore.build();
    }
}
