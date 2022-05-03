package src.main.java.com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoicesStatusMapper {

    default InvoicesStatusDto toDto(InvoicesStatus invoicesStatus) {
        InvoicesStatusDto invoicesStatusDto = new InvoicesStatusDto();

        if (invoicesStatus == null) {
            return null;
        } else {
            invoicesStatusDto.setId(invoicesStatus.getId());
            invoicesStatusDto.setStatusName(invoicesStatus.getStatusName());
            return invoicesStatusDto;
        }
    }

    default InvoicesStatus toModel(InvoicesStatusDto invoicesStatusDto) {
        if (invoicesStatusDto == null) {
            return null;
        }
        return InvoicesStatus.builder()
                .id(invoicesStatusDto.getId())
                .statusName(invoicesStatusDto.getStatusName())
                .build();
    }
}
