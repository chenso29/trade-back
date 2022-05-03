package src.main.java.com.trade_accounting.services.interfaces.client;

import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.services.interfaces.util.AbstractService;

public interface DepartmentService extends AbstractService<DepartmentDto> {

    DepartmentDto getByName(String name);

}
