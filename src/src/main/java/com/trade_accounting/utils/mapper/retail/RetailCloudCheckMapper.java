package src.main.java.com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.retail.RetailCloudCheck;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.entity.units.Currency;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface RetailCloudCheckMapper {
    default RetailCloudCheckDto toDto(RetailCloudCheck retailCloudCheck) {
        if (retailCloudCheck == null) {
            return null;
        }

        RetailCloudCheckDto.RetailCloudCheckDtoBuilder retailCloudCheckDtoBuilder = RetailCloudCheckDto.builder();

        retailCloudCheckDtoBuilder.id(retailCloudCheck.getId());
        retailCloudCheckDtoBuilder.date(String.valueOf(retailCloudCheck.getDate()));
        retailCloudCheckDtoBuilder.initiatorId(retailCloudCheck.getId());
        retailCloudCheckDtoBuilder.fiscalizationPointId(retailCloudCheck.getId());
        retailCloudCheckDtoBuilder.status(retailCloudCheck.getStatus());
        retailCloudCheckDtoBuilder.cheskStatus(retailCloudCheck.getCheskStatus());
        retailCloudCheckDtoBuilder.total(retailCloudCheck.getTotal());
        retailCloudCheckDtoBuilder.currencyId(retailCloudCheck.getId());
        retailCloudCheckDtoBuilder.cashierId(retailCloudCheck.getId());

        return retailCloudCheckDtoBuilder.build();
    }

    default RetailCloudCheck toModel (RetailCloudCheckDto retailCloudCheckDto){
        if (retailCloudCheckDto == null) {
            return null;
        }

        RetailCloudCheck.RetailCloudCheckBuilder retailCloudCheckBuilder = RetailCloudCheck.builder();

        retailCloudCheckBuilder.id(retailCloudCheckDto.getId());
        retailCloudCheckBuilder.date(LocalDateTime.parse(retailCloudCheckDto.getDate()));
        retailCloudCheckBuilder.initiator(retailCloudCheckDtoToRetailStoreInitiator(retailCloudCheckDto));
        retailCloudCheckBuilder.fiscalizationPoint(retailCloudCheckDtoToFiscalizationPoint(retailCloudCheckDto));
        retailCloudCheckBuilder.status(retailCloudCheckDto.getStatus());
        retailCloudCheckBuilder.cheskStatus(retailCloudCheckDto.getCheskStatus());
        retailCloudCheckBuilder.total(retailCloudCheckDto.getTotal());
        retailCloudCheckBuilder.currency(retailCloudCheckDtoToCurrency(retailCloudCheckDto));
        retailCloudCheckBuilder.cashier(retailCloudCheckDtoToEmployee(retailCloudCheckDto));

        return retailCloudCheckBuilder.build();
    }

    private RetailStore retailCloudCheckDtoToRetailStoreInitiator(RetailCloudCheckDto retailCloudCheckDto) {
        if ( retailCloudCheckDto == null ) {
            return null;
        }
        RetailStore.RetailStoreBuilder retailStore = RetailStore.builder();
        retailStore.id( retailCloudCheckDto.getInitiatorId() );
        return retailStore.build();
    }

    private RetailStore retailCloudCheckDtoToFiscalizationPoint(RetailCloudCheckDto retailCloudCheckDto) {
        if ( retailCloudCheckDto == null ) {
            return null;
        }
        RetailStore.RetailStoreBuilder retailStore = RetailStore.builder();
        retailStore.id( retailCloudCheckDto.getInitiatorId() );
        return retailStore.build();
    }

    private Employee retailCloudCheckDtoToEmployee(RetailCloudCheckDto retailCloudCheckDto) {
        if ( retailCloudCheckDto == null ) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId( retailCloudCheckDto.getCashierId() );
        return employee;
    }

    private Currency retailCloudCheckDtoToCurrency (RetailCloudCheckDto retailCloudCheckDto) {
        if ( retailCloudCheckDto == null ) {
            return null;
        }
        Currency currency = new Currency();
        currency.setId( retailCloudCheckDto.getCurrencyId() );
        return currency;
    }
}

