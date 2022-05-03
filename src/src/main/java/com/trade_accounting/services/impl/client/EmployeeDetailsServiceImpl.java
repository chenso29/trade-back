package src.main.java.com.trade_accounting.services.impl.client;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.repositories.client.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeEmail) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByEmail(employeeEmail);
        return employee.orElseThrow(() -> new UsernameNotFoundException(employeeEmail));
    }
}
