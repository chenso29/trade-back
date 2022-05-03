package src.main.java.com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.RequestsProductions;
import com.trade_accounting.models.dto.production.RequestsProductionsDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface RequestsProductionsMapper {

    default RequestsProductionsDto toDto(RequestsProductions requestsProductions) {
        RequestsProductionsDto requestsProductionsDto = new RequestsProductionsDto();
        if (requestsProductions == null || requestsProductions.getWarehouse().getId() == null
                || requestsProductions.getTechnicalCard().getId() == null) {
            return null;
        } else {
            requestsProductionsDto.setId(requestsProductions.getId());
            requestsProductionsDto.setDateOfTheCertificate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(requestsProductions.getDateOfTheCertificate()));
            requestsProductionsDto.setNumberOfTheCertificate(requestsProductions.getNumberOfTheCertificate());
            requestsProductionsDto.setVolume(requestsProductions.getVolume());
            requestsProductionsDto.setWarehouseId(requestsProductions.getWarehouse().getId());
            requestsProductionsDto.setTechnicalCardId(requestsProductions.getTechnicalCard().getId());
            return requestsProductionsDto;
        }
    }

    default RequestsProductions toModel(RequestsProductionsDto requestsProductionsDto) {
        if (requestsProductionsDto == null) {
            return null;
        }

        return RequestsProductions.builder()
                .id(requestsProductionsDto.getId())
                .numberOfTheCertificate(requestsProductionsDto.getNumberOfTheCertificate())
                .volume(requestsProductionsDto.getVolume())
                .build();
    }
}
