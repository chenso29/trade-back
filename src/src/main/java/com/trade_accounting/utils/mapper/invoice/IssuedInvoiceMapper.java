package src.main.java.com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.IssuedInvoice;
import com.trade_accounting.models.dto.invoice.IssuedInvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IssuedInvoiceMapper {
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "payment.id", target = "paymentId")
    })
    IssuedInvoiceDto toDto(IssuedInvoice issuedInvoice);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "paymentId", target = "payment.id")
    })
    IssuedInvoice toModel(IssuedInvoiceDto issuedInvoiceDto);

//    default IssuedInvoiceDto toDto(IssuedInvoice issuedInvoice) {
//        if ( issuedInvoice == null ) {
//            return null;
//        }
//
//        IssuedInvoiceDto.IssuedInvoiceDtoBuilder issuedInvoiceDto = IssuedInvoiceDto.builder();
//
//        issuedInvoiceDto.companyId( issuedInvoiceCompanyId( issuedInvoice ) );
//        issuedInvoiceDto.contractorId( issuedInvoiceContractorId( issuedInvoice ) );
//        issuedInvoiceDto.id( issuedInvoice.getId() );
//        issuedInvoiceDto.comment( issuedInvoice.getComment() );
//        if ( issuedInvoice.getDate() != null ) {
//            issuedInvoiceDto.date( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( issuedInvoice.getDate() ) );
//        }
//        issuedInvoiceDto.isSpend( issuedInvoice.getIsSpend() );
//
//        return issuedInvoiceDto.build();
//    }
//
//    default IssuedInvoice toModel(IssuedInvoiceDto issuedInvoiceDto) {
//        if ( issuedInvoiceDto == null ) {
//            return null;
//        }
//
//        IssuedInvoice.IssuedInvoiceBuilder issuedInvoice = IssuedInvoice.builder();
//
//        issuedInvoice.company( issuedInvoiceDtoToCompany( issuedInvoiceDto ) );
//        issuedInvoice.contractor( issuedInvoiceDtoToContractor( issuedInvoiceDto ) );
//        issuedInvoice.id( issuedInvoiceDto.getId() );
//        if ( issuedInvoiceDto.getDate() != null ) {
//            issuedInvoice.date( LocalDateTime.parse( issuedInvoiceDto.getDate() ) );
//        }
//        issuedInvoice.isSpend( issuedInvoiceDto.getIsSpend() );
//        issuedInvoice.comment( issuedInvoiceDto.getComment() );
//
//        return issuedInvoice.build();
//    }
//
//    private Long issuedInvoiceCompanyId(IssuedInvoice issuedInvoice) {
//        if ( issuedInvoice == null ) {
//            return null;
//        }
//        Company company = issuedInvoice.getCompany();
//        if ( company == null ) {
//            return null;
//        }
//        Long id = company.getId();
//        if ( id == null ) {
//            return null;
//        }
//        return id;
//    }
//
//    private Long issuedInvoiceContractorId(IssuedInvoice issuedInvoice) {
//        if ( issuedInvoice == null ) {
//            return null;
//        }
//        Contractor contractor = issuedInvoice.getContractor();
//        if ( contractor == null ) {
//            return null;
//        }
//        Long id = contractor.getId();
//        if ( id == null ) {
//            return null;
//        }
//        return id;
//    }
//
//    private Company issuedInvoiceDtoToCompany(IssuedInvoiceDto issuedInvoiceDto) {
//        if ( issuedInvoiceDto == null ) {
//            return null;
//        }
//
//        Company company = new Company();
//
//        company.setId( issuedInvoiceDto.getCompanyId() );
//
//        return company;
//    }
//
//    private Contractor issuedInvoiceDtoToContractor(IssuedInvoiceDto issuedInvoiceDto) {
//        if ( issuedInvoiceDto == null ) {
//            return null;
//        }
//
//        Contractor contractor = new Contractor();
//
//        contractor.setId( issuedInvoiceDto.getContractorId() );
//
//        return contractor;
//    }
}
