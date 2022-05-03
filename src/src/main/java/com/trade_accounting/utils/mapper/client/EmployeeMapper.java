package src.main.java.com.trade_accounting.utils.mapper.client;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.client.Role;
import com.trade_accounting.models.dto.client.EmployeeDto;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    default Employee toModel(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }

        return Employee.builder()
                .id(employeeDto.getId())
                .lastName(employeeDto.getLastName())
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .sortNumber(employeeDto.getSortNumber())
                .phone(employeeDto.getPhone())
                .inn(employeeDto.getInn())
                .description(employeeDto.getDescription())
                .email(employeeDto.getEmail())
                .password(employeeDto.getPassword())
                .build();
    }

    default EmployeeDto toDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();
        if (employee == null) {
            return null;
        } else {

        employeeDto.setId(employee.getId());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setMiddleName(employee.getMiddleName());
        employeeDto.setSortNumber(employee.getSortNumber());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setInn(employee.getInn());
        employeeDto.setDescription(employee.getDescription());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPassword(employee.getPassword());

        employeeDto.setRoleDtoIds(
                employee.getRoles().stream()
                        .map(Role::getId)
                        .collect(Collectors.toSet())
        );

        if (employee.getDepartment() == null) {
            employeeDto.setDepartmentDtoId(null); //можно реализовать отдел по умолчанию и присваивать его
        } else {
            employeeDto.setDepartmentDtoId(employee.getDepartment().getId());
        }

        if (employee.getPosition() == null) {
            employeeDto.setPositionDtoId(null); //можно реализовать должность по умолчанию и присваивать её
        } else {
            employeeDto.setPositionDtoId(employee.getPosition().getId());
        }

        if (employee.getImage() == null) {
            employeeDto.setImageDtoId(null); //можно реализовать изображение сотрудника по умолчанию и присваивать его
        } else {
            employeeDto.setImageDtoId(employee.getImage().getId());
        }

        return employeeDto;

        }
    }
}
