package src.main.java.com.trade_accounting.utils.mapper.production;


import com.trade_accounting.models.entity.production.TechnicalOperations;
import com.trade_accounting.models.dto.production.TechnicalOperationsDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TechnicalOperationsMapper {

//    TechnicalOperationsDto toDto(TechnicalOperations technicalOperations);
//
//    TechnicalOperations toModel(TechnicalOperationsDto technicalOperationsDto);
//

    default TechnicalOperations toModel(TechnicalOperationsDto technicalOperationsDto){
        if (technicalOperationsDto == null) {
            return null;
        }

        return TechnicalOperations.builder()
//                .id(technicalOperationsDto.getId())
//                .number(technicalOperationsDto.getNumber())
                .volume(technicalOperationsDto.getVolume())
                .build();
    }

    default TechnicalOperationsDto toDto(TechnicalOperations technicalOperations) {
        TechnicalOperationsDto technicalOperationsDto = new TechnicalOperationsDto();
        if (technicalOperations == null) {
            return null;
        }

        technicalOperationsDto.setId(technicalOperations.getId());
        technicalOperationsDto.setComment(technicalOperations.getComment());
        technicalOperationsDto.setIsPrint(technicalOperations.getIsPrint());
        technicalOperationsDto.setIsSent(technicalOperations.getIsSent());
        technicalOperationsDto.setIsRecyclebin(technicalOperations.getIsRecyclebin());
//        if(technicalOperations.getDate() != null){
//            technicalOperationsDto.setDate(DateTimeFormatter.ISO_LOCAL_DATE.format(technicalOperations.getDate()));
//        }
        technicalOperationsDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(technicalOperations.getDate()));
        technicalOperationsDto.setTechnicalCard(technicalOperations.getTechnicalCard().getId());
        technicalOperationsDto.setVolume(technicalOperations.getVolume());
        technicalOperationsDto.setWarehouse(technicalOperations.getWarehouse().getId());
        technicalOperationsDto.setCompanyId(technicalOperations.getCompany().getId());

        return technicalOperationsDto;
    }
}
