package src.main.java.com.trade_accounting.services.interfaces.retail;

import com.trade_accounting.models.entity.retail.RetailCloudCheck;
import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface RetailCloudCheckService extends AbstractService<RetailCloudCheckDto>, SearchableService<RetailCloudCheck, RetailCloudCheckDto> {
}
