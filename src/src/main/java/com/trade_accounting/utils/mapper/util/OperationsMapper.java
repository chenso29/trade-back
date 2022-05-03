package src.main.java.com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.dto.util.OperationsDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface OperationsMapper {
    default OperationsDto toDto(OperationsAbstract operationsAbstract) {
        OperationsDto operationsAbstractDto = new OperationsDto();
        if (operationsAbstract == null) {
            return null;
        } else {
            operationsAbstractDto.setId(operationsAbstract.getId());
            operationsAbstractDto.setComment(operationsAbstract.getComment());
            operationsAbstractDto.setIsSent(operationsAbstract.getIsSent());
            operationsAbstractDto.setIsPrint(operationsAbstract.getIsPrint());
            operationsAbstractDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(operationsAbstract.getDate()));
            operationsAbstractDto.setIsRecyclebin(operationsAbstract.getIsRecyclebin());
            Company company = operationsAbstract.getCompany();
            if (operationsAbstract.getCompany() == null) {
                return null;
            } else {
                operationsAbstractDto.setCompanyId(company.getId());
                    return operationsAbstractDto;
                }
            }
        }

    }


