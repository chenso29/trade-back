package src.main.java.com.trade_accounting.services.impl.client;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.models.entity.client.Position;
import com.trade_accounting.models.entity.client.Role;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.dto.util.PageDto;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.util.ImageRepository;
import com.trade_accounting.repositories.client.PositionRepository;
import com.trade_accounting.repositories.client.RoleRepository;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import com.trade_accounting.utils.mapper.client.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final ImageRepository imageRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> search(Specification<Employee> specification) {
        return executeSearch(employeeRepository, employeeMapper::toDto, specification);
    }

    @Override
    public PageDto<EmployeeDto> search(Specification<Employee> specification, Pageable pageParams) {
        Page<Employee> page = employeeRepository.findAll(specification, pageParams);
        return new PageDto<>(
                page.getContent().stream().map(employeeMapper::toDto).collect(Collectors.toList()),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumberOfElements()
        );
    }

    @Override
    public EmployeeDto getById(Long id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        return employeeMapper.toDto(emp.orElse(new Employee()));
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        return saveOrUpdate(employeeDto);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        return saveOrUpdate(employeeDto);
    }

    private EmployeeDto saveOrUpdate(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toModel(employeeDto);

        Department department = departmentRepository.getDepartmentById(employeeDto.getDepartmentDtoId());
        Position position = positionRepository.getPositionById(employeeDto.getPositionDtoId());
        Image image = imageRepository.getImageById(employeeDto.getImageDtoId());
        if (image == null || employeeDto.getImageDtoId() == 1) {
            image = imageRepository.save(Image.builder()
                    .id(employeeDto.getImageDtoId())
                    .imageUrl("src/main/resources/file/male.ico")
                    .build());
        }

        Set<Role> roles = employeeDto.getRoleDtoIds().stream()
                .map(roleRepository::getRoleById)
                .collect(Collectors.toSet());

        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setImage(image);
        employee.setRoles(roles);

        return employeeMapper.toDto(employeeRepository.save(employee));
    }


    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent() && !employee.get().getImage().getImageUrl().equals("src/main/resources/file/male.ico")) {
            Files.deleteIfExists(Paths.get(employee.get().getImage().getImageUrl()));
        }
        employee.get().setImage(null);
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto getByEmail(String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if (employee.isEmpty()) {
            return new EmployeeDto();
        }
        return employeeMapper.toDto(employee.get());
    }

    @Override
    public List<EmployeeDto> searchByString(String text) {
        return employeeRepository.getBySearch(text).stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());

    }
}
