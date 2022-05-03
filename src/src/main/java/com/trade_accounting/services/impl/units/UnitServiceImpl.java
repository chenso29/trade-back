package src.main.java.com.trade_accounting.services.impl.units;

import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.units.UnitRepository;
import com.trade_accounting.services.interfaces.client.DepartmentService;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import com.trade_accounting.services.interfaces.units.UnitService;
import com.trade_accounting.utils.SortNumberConverter;
import com.trade_accounting.utils.mapper.units.UnitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final UnitMapper unitMapper;

    @Override
    public List<UnitDto> getAll() {
        return unitRepository.findAll().stream()
                .map(unitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UnitDto getById(Long id) {
        return unitMapper.toDto(unitRepository.findById(id).get());
    }

    @Override
    public UnitDto create(UnitDto unitDto) {
        Unit unit = unitMapper.toModel(unitDto);
        unit.setDepartmentOwner(employeeRepository.findByEmail(getPrincipalName()).get().getDepartment().getName());
        unit.setEmployeeChange(getPrincipalFullName());
        unit.setEmployeeOwner(getPrincipalFullName());
        return unitMapper.toDto(unitRepository.save(unit));
    }

    @Override
    public UnitDto update(UnitDto unitDto) {
        Unit unit = unitMapper.toModel(unitDto);
        unit.setEmployeeChange(getPrincipalFullName());
        unitRepository.save(unit);
        return unitDto;
    }

    @Override
    public void deleteById(Long id) {
        unitRepository.deleteById(id);
    }

    @Override
    public List<UnitDto> search(Specification<Unit> spec) {
        return executeSearch(unitRepository, unitMapper::toDto, spec);
    }

    @Override
    public List<UnitDto> searchByString(String text) {
        return unitRepository.getBySearch(text).stream()
                .map(unitMapper::toDto)
                .collect(Collectors.toList());

    }

    private String getPrincipalName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    private String getPrincipalFullName(){
        String principalFullName = "";
        for (EmployeeDto employeeDto : employeeService.getAll()) {
            if (Objects.equals(getPrincipalName(), employeeDto.getEmail())) {
                principalFullName = employeeDto.getFirstName().concat(" ").concat(employeeDto.getLastName());
            }
        }
        return principalFullName;
    }

    @Override
    public void moveToRecyclebin(long id) {
        Unit unit = unitRepository.getOne(id);
        unit.setIsRecyclebin(true);
        unitRepository.save(unit);
    }
}
