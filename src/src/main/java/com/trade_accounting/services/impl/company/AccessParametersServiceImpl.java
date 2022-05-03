package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.AccessParameters;
import com.trade_accounting.models.dto.company.AccessParametersDto;
import com.trade_accounting.repositories.company.AccessParametersRepository;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.services.interfaces.company.AccessParametersService;
import com.trade_accounting.utils.mapper.company.AccessParametersMapper;
import com.trade_accounting.utils.mapper.client.DepartmentMapper;
import com.trade_accounting.utils.mapper.client.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccessParametersServiceImpl implements AccessParametersService {

    private final AccessParametersRepository accessParametersRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;
    private final AccessParametersMapper accessParametersMapper;

    @Override
    public List<AccessParametersDto> getAll() {
        return accessParametersRepository.findAll().stream()
                .map(accessParametersMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccessParametersDto getById(Long id) {
        return accessParametersMapper.toDto(accessParametersRepository.getOne(id));
    }

    @Override
    public AccessParametersDto create(AccessParametersDto dto) {
        return accessParametersMapper.toDto(accessParametersRepository
                .save(AccessParameters.builder().id(dto.getId()).generalAccess(dto.getGeneralAccess())
                        .employee(employeeRepository.getOne(dto.getEmployeeId()))
                        .department(departmentMapper.toModel(departmentRepository.getById(dto.getDepartmentId()))).build()));
    }

    @Override
    public AccessParametersDto update(AccessParametersDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        accessParametersRepository.deleteById(id);
    }
}
