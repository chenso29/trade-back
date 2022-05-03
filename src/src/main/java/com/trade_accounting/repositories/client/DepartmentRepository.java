package src.main.java.com.trade_accounting.repositories.client;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.dto.client.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

    Optional<Department> findByName(String name);

    @Query("select new com.trade_accounting.models.dto.client.DepartmentDto(" +
            "d.id, " +
            "d.name, " +
            "d.sortNumber" +
            ") " +
            "from Department d")
    List<DepartmentDto> getAll();

    @Query("select new com.trade_accounting.models.dto.client.DepartmentDto(" +
            "d.id, " +
            "d.name, " +
            "d.sortNumber" +
            ") " +
            "from Department d " +
            "where d.id = :id")
    DepartmentDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.client.DepartmentDto(" +
            "d.id, " +
            "d.name, " +
            "d.sortNumber" +
            ") " +
            "from Department d " +
            "where d.name = :name")
    DepartmentDto getByName(@Param("name") String name);

    @Query("select new com.trade_accounting.models.dto.client.DepartmentDto(" +
            "em.department.id, " +
            "em.department.name, " +
            "em.department.sortNumber" +
            ") " +
            "from Employee em " +
            "where em.id = :id")
    DepartmentDto getDepartmentByEmployeeId(@Param("id") Long id);

    Department getDepartmentById(Long id);
}
