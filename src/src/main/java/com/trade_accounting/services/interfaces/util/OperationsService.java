package src.main.java.com.trade_accounting.services.interfaces.util;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.dto.util.OperationsDto;

import java.util.List;

public interface OperationsService extends AbstractService<OperationsDto>,
        SearchableService<OperationsAbstract, OperationsDto> {

    List<OperationsDto> quickSearch(String text);

    List<OperationsDto> quickSearchRecycle(String text);

}