package src.main.java.com.trade_accounting.services.interfaces.client;

import com.trade_accounting.models.dto.client.RoleDto;
import com.trade_accounting.services.interfaces.util.AbstractService;

public interface RoleService extends AbstractService<RoleDto> {

    RoleDto getByName(String name);
}
