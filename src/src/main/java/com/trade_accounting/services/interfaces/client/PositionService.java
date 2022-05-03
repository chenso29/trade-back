package src.main.java.com.trade_accounting.services.interfaces.client;

import com.trade_accounting.models.dto.client.PositionDto;
import com.trade_accounting.services.interfaces.util.AbstractService;

public interface PositionService extends AbstractService<PositionDto> {

    PositionDto getByName(String name);
}
