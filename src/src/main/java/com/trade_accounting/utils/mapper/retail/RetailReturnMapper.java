package src.main.java.com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailReturn;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.retail.RetailReturnDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface RetailReturnMapper {

    default RetailReturnDto toDto(RetailReturn retailReturn) {
        if (retailReturn == null) {
            return null;
        }
        RetailReturnDto.RetailReturnDtoBuilder retailReturnDto = RetailReturnDto.builder();

        retailReturnDto.id(retailReturn.getId());
        if (retailReturn.getDate() != null) {
            retailReturnDto.date(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(retailReturn.getDate()));
        }

        retailReturnDto.retailStoreId(retailReturnRetailStoreId(retailReturn));
        retailReturnDto.cashAmount(retailReturn.getCashAmount());
        retailReturnDto.cashlessAmount(retailReturn.getCashlessAmount());
        retailReturnDto.totalAmount(retailReturn.getCashAmount().add(retailReturn.getCashlessAmount()));
        retailReturnDto.isSpend(retailReturn.getIsSpend());
        retailReturnDto.isSend(retailReturn.getIsSend());
        retailReturnDto.isPrint(retailReturn.getIsPrint());
        retailReturnDto.comment(retailReturn.getComment());

        return retailReturnDto.build();
    }

    default RetailReturn toModel(RetailReturnDto emp) {
        if (emp == null) {
            return null;
        }
        RetailReturn.RetailReturnBuilder retailReturn = RetailReturn.builder();

        retailReturn.id(emp.getId());
        if (emp.getDate() != null) {
            retailReturn.date(LocalDateTime.parse(emp.getDate()));
        }
        retailReturn.retailStore(retailReturnDtoToRetailStore(emp));
        retailReturn.cashAmount(emp.getCashAmount());
        retailReturn.cashlessAmount(emp.getCashlessAmount());
        retailReturn.isSpend(emp.getIsSpend());
        retailReturn.isSend(emp.getIsSend());
        retailReturn.isPrint(emp.getIsPrint());
        retailReturn.comment(emp.getComment());

        return retailReturn.build();
    }

    private Long retailReturnRetailStoreId(RetailReturn retailReturn) {
        if (retailReturn == null) {
            return null;
        }
        RetailStore retailStore = retailReturn.getRetailStore();
        if (retailStore == null) {
            return null;
        }
        Long id = retailStore.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    default RetailStore retailReturnDtoToRetailStore(RetailReturnDto retailReturnDto) {
        if (retailReturnDto == null) {
            return null;
        }
        RetailStore.RetailStoreBuilder retailStore = RetailStore.builder();
        retailStore.id(retailReturnDto.getRetailStoreId());

        return retailStore.build();
    }
}
