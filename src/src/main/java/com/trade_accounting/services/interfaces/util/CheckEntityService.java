package src.main.java.com.trade_accounting.services.interfaces.util;

import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

public interface CheckEntityService {

    void checkForBadEmployee(EmployeeDto employee);

    void checkForBadCompany(CompanyDto company);

    void checkExists(JpaRepository<Entity, Long> repository, Long id);
}
