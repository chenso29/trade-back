package src.main.java.com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.invoice.InvoiceReceived;
import com.trade_accounting.models.dto.invoice.InvoiceReceivedDto;
import com.trade_accounting.models.entity.warehouse.Acceptance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface InvoiceReceivedMapper {
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "acceptance.id", target = "acceptanceId")
    })
    default InvoiceReceivedDto toDto(InvoiceReceived invoiceReceived){
        if( invoiceReceived == null){
            return null;
        }
        InvoiceReceivedDto.InvoiceReceivedDtoBuilder invoiceReceivedDto = InvoiceReceivedDto.builder();

        invoiceReceivedDto.id( invoiceReceived.getId() );
        if ( invoiceReceived.getData() != null ) {
            invoiceReceivedDto.data( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( invoiceReceived.getData() ) );
        }
        invoiceReceivedDto.companyId( invoiceReceivedCompanyId( invoiceReceived ) );
        invoiceReceivedDto.contractorId( invoiceReceivedContractorId( invoiceReceived ) );

        invoiceReceivedDto.acceptanceId( invoiceReceivedAcceptanceId( invoiceReceived ) );
        invoiceReceivedDto.incomNumber(invoiceReceived.getIncomNumber());
        invoiceReceivedDto.incomData(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(invoiceReceived.getIncomData()));
        invoiceReceivedDto.isSend(invoiceReceived.getIsSend());
        invoiceReceivedDto.isPrint(invoiceReceived.getIsPrint());
        invoiceReceivedDto.isSpend(invoiceReceived.getIsSpend());
        invoiceReceivedDto.comment( invoiceReceived.getComment() );

        return invoiceReceivedDto.build();
    }

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "acceptanceId", target = "acceptance.id")
    })
    InvoiceReceived toModel(InvoiceReceivedDto invoiceReceivedDto);

    private Long invoiceReceivedCompanyId(InvoiceReceived invoiceReceived) {
        if ( invoiceReceived == null ) {
            return null;
        }
        Company company = invoiceReceived.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long invoiceReceivedContractorId(InvoiceReceived invoiceReceived) {
        if ( invoiceReceived == null ) {
            return null;
        }
        Contractor contractor = invoiceReceived.getContractor();
        if ( contractor == null ) {
            return null;
        }
        Long id = contractor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long invoiceReceivedAcceptanceId(InvoiceReceived invoiceReceived) {
        if (invoiceReceived == null) {
            return null;
        }
        Acceptance acceptance = invoiceReceived.getAcceptance();
        if (acceptance == null) {
            return null;
        }
        Long id = acceptance.getId();
        if (id == null) {
            return null;
        }
        return id;
    }
}
