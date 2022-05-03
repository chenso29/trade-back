package src.main.java.com.trade_accounting.services.impl.client;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.services.interfaces.client.DepartmentService;
import com.trade_accounting.utils.mapper.client.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getById(Long id) {
        return departmentMapper.toDto(
                departmentRepository.findById(id).orElse(new Department())
        );
    }

    @Override
    public DepartmentDto getByName(String name) {
        return departmentMapper.toDto(
                departmentRepository.findByName(name).orElse(new Department())
        );
    }

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
        departmentDto.setId(departmentMapper.toModel(departmentDto).getId());
        return departmentMapper.toDto(
                departmentRepository.save(departmentMapper.toModel(departmentDto))
        );

    }


    @Override
    public DepartmentDto update(DepartmentDto departmentDto) {
        return create(departmentDto);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
