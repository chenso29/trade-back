package src.main.java.com.trade_accounting.services.interfaces.warehouse;


import com.trade_accounting.models.dto.warehouse.SerialNumbersDto;
import com.trade_accounting.models.entity.warehouse.SerialNumbers;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SerialNumbersService extends AbstractService<SerialNumbersDto>, SearchableService<SerialNumbers, SerialNumbersDto> {

    List<SerialNumbersDto> search(Specification<SerialNumbers> spec);

    List<SerialNumbersDto> searchByFilter(String search);
}
