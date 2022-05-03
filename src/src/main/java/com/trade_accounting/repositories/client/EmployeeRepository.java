package src.main.java.com.trade_accounting.repositories.client;

import com.trade_accounting.models.entity.client.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmail(String email);

    Employee findByFirstName(String firstName);

    @Query("from Employee e " +
            "where lower ( concat(e.firstName, ' ', e.middleName, ' ', e.lastName, ' ', e.email, ' ', e.phone, ' ', e.department.name, ' ', e.position.name)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<Employee> getBySearch(@Param("symbols") String search);

}
