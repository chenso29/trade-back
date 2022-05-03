package src.main.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.TechnicalProcess;
import com.trade_accounting.models.dto.production.TechnicalProcessDto;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.production.StagesProductionRepository;
import com.trade_accounting.repositories.production.TechnicalProcessRepository;
import com.trade_accounting.services.interfaces.production.TechnicalProcessService;
import com.trade_accounting.utils.mapper.production.TechnicalProcessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TechnicalProcessServiceImpl implements TechnicalProcessService {

    private final TechnicalProcessRepository technicalProcessRepository;
    private final TechnicalProcessMapper technicalProcessMapper;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final StagesProductionRepository stagesProductionRepository;


    @Override
    public List<TechnicalProcessDto> getAll() {
        return technicalProcessRepository.findAll().stream()
                .map(technicalProcessMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalProcessDto getById(Long id) {
        return technicalProcessMapper.toDto(technicalProcessRepository.getOne(id));
    }

    @Override
    public TechnicalProcessDto create(TechnicalProcessDto dto) {
        TechnicalProcess technicalProcess = technicalProcessMapper.toModel(dto);
        technicalProcess.setStagesProductionSet(
                stagesProductionRepository.getStagesProductionsByIdIn(dto.getStagesProductionIds()));
        technicalProcess.setDepartmentOwner(departmentRepository.getOne(dto.getDepartmentOwnerId()));
        technicalProcess.setEmployeeOwner(employeeRepository.getOne(dto.getEmployeeOwnerId()));
        technicalProcess.setEmployeeWhoLastChanged(employeeRepository.getOne(dto.getEmployeeWhoLastChangedId()));
        return technicalProcessMapper.toDto(technicalProcessRepository.save(technicalProcess));
    }

    @Override
    public TechnicalProcessDto update(TechnicalProcessDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        technicalProcessRepository.deleteById(id);
    }

    @Override
    public List<TechnicalProcessDto> search(String request) {
        return technicalProcessRepository.search(request).stream()
                .map(technicalProcessMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TechnicalProcessDto getByName(String name) {
        return technicalProcessMapper.toDto(technicalProcessRepository.getByName(name).orElse(null));
    }

    @Override
    public List<TechnicalProcessDto> search(Specification<TechnicalProcess> spec) {
        List<TechnicalProcess> technicalProcessList = technicalProcessRepository.findAll(spec);
        List<TechnicalProcessDto> technicalProcessDtoList = new ArrayList<>();
        for (TechnicalProcess tp : technicalProcessList) {
            technicalProcessDtoList.add(technicalProcessMapper.toDto(tp));
        }
        return technicalProcessDtoList;
    }
}
