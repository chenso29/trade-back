package src.test.java.com.trade_accounting.services.impl.client;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.util.ImageRepository;
import com.trade_accounting.repositories.client.PositionRepository;
import com.trade_accounting.repositories.client.RoleRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.client.EmployeeDtoStubs;
import com.trade_accounting.utils.mapper.client.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ImageRepository imageRepository;

    @Spy
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    //Tests
    @Test
    void getAll_shouldReturnListFilledEmployeeDto() {
        when(employeeRepository.findAll())
                .thenReturn(
                        Stream.of(
                               ModelStubs.getEmployee(1L),
                                ModelStubs.getEmployee(2L),
                                ModelStubs.getEmployee(1L)
                        ).collect(Collectors.toList())
                );

        List<EmployeeDto> employees = employeeService.getAll();

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertTrue(employees.size() > 0, "failure - expected that a size of list of employeeDto greater than 0");

        for (EmployeeDto employee : employees) {
            employeeDtoIsCorrectlyInited(employee);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListEmployeeDto() {
        when(employeeRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<EmployeeDto> employees = employeeService.getAll();

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertEquals(0, employees.size(), "failure - expected that size of list of employeeDto equals 0");
    }

    @Test
    void search_shouldReturnListFilledEmployeeDto() {
        when(employeeRepository.findAll(Mockito.<Specification<Employee>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getEmployee(1L),
                                ModelStubs.getEmployee(2L),
                                ModelStubs.getEmployee(3L)
                        ).collect(Collectors.toList())
                );

        List<EmployeeDto> employees = employeeService
                .search(SpecificationStubs.getEmployeeSpecificationStub());

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertTrue(employees.size() > 0, "failure - expected that a list of employeeDto greater than 0");

        for (EmployeeDto employee : employees) {
            employeeDtoIsCorrectlyInited(employee);
        }
    }

    @Test
    void search_shouldReturnEmptyListEmployeeDto() {
        when(employeeRepository.findAll(Mockito.<Specification<Employee>>any()))
                .thenReturn(new ArrayList<>());

        List<EmployeeDto> employees = employeeService
                .search(SpecificationStubs.getEmployeeSpecificationStub());

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertEquals(0, employees.size(), "failure - expected that size of list of employeeDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledEmployeeDto() {
        Optional<Employee> employeeFromRepo = Optional.of(
                ModelStubs.getEmployee(1L)
        );

        when(employeeRepository.findById(anyLong()))
                .thenReturn(employeeFromRepo);

        EmployeeDto employee = employeeService.getById(1L);

        assertNotNull(employee, "failure - expected that employee not null.");
        employeeDtoIsCorrectlyInited(employee);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        employeeService.create(
                EmployeeDtoStubs.getEmployeeDto(1L)
        );
        verify(roleRepository, times(3)).findById(anyLong());
        verify(employeeRepository).save(any(Employee.class));
        verify(departmentRepository).findById(anyLong());
        verify(positionRepository).findById(anyLong());
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        employeeService.create(EmployeeDtoStubs.getEmployeeDto(1L));

        verify(roleRepository, times(3)).findById(anyLong());
        verify(employeeRepository).save(any(Employee.class));
        verify(departmentRepository).findById(anyLong());
        verify(positionRepository).findById(anyLong());
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        employeeService.deleteById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void getByEmail_shouldReturnFilledEmployeeDto() {
        Optional<Employee> employeeFromRepo = Optional.of(
                ModelStubs.getEmployee(1L)
        );

        when(employeeRepository.findByEmail(anyString()))
                .thenReturn(employeeFromRepo);

        EmployeeDto employee = employeeService.getByEmail("email@email.ru");

        assertNotNull(employee, "failure - expected that employee not null.");

        employeeDtoIsCorrectlyInited(employee);
    }

    @Test
    void getByEmail_shouldReturnEmptyEmployeeDto() {
        Optional<Employee> employeeFromRepo = Optional.empty();

        when(employeeRepository.findByEmail(anyString()))
                .thenReturn(employeeFromRepo);

        EmployeeDto employee = employeeService.getByEmail("email@email.ru");

        assertEquals(new EmployeeDto(), employee, "failure - expected that employee was empty");
    }


    void employeeDtoIsCorrectlyInited(EmployeeDto employee) {
        assertNotNull(employee, "Fail in passed employee");
        assertNotNull(employee.getId(), "Fail in field 'id' of employee");
        assertNotNull(employee.getLastName(), "Fail in field 'lastName' of employee");
        assertNotNull(employee.getEmail(), "Fail in field 'email' of employee");
        assertNotNull(employee.getRoleDtoIds(), "Fail in field 'roleDto' of employee");
        assertTrue(employee.getRoleDtoIds().size() >= 1, "Expected that size of EmployeeDto role list greater than 0");
    }
}

