package src.main.java.com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface CompanyService extends AbstractService<CompanyDto>, SearchableService<Company, CompanyDto> {

    CompanyDto getByEmail(String email);
}
