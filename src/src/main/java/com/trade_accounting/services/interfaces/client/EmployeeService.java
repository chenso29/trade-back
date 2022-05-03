package src.main.java.com.trade_accounting.services.interfaces.client;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.dto.util.PageDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface EmployeeService extends SearchableService<Employee, EmployeeDto>, AbstractService<EmployeeDto> {

    PageDto<EmployeeDto> search(Specification<Employee> specification, Pageable page);

    EmployeeDto getByEmail(String email);

    List<EmployeeDto> searchByString(String text);
}

