package src.main.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.entity.util.BonusProgram;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.entity.retail.RetailOperationWithPoints;
import com.trade_accounting.models.entity.util.Task;
import com.trade_accounting.models.dto.retail.RetailOperationWithPointsDto;
import com.trade_accounting.repositories.util.BonusProgramRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.util.FileRepository;
import com.trade_accounting.repositories.retail.RetailOperationWithPointsRepository;
import com.trade_accounting.repositories.util.TaskRepository;
import com.trade_accounting.services.interfaces.retail.RetailOperationWithPointsService;
import com.trade_accounting.utils.mapper.retail.RetailOperationWithPointsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RetailOperationWithPointsServiceImpl implements RetailOperationWithPointsService {

    private final RetailOperationWithPointsRepository retailOperationWithPointsRepository;
    private final BonusProgramRepository bonusProgramRepository;
    private final ContractorRepository contractorRepository;
    private final TaskRepository taskRepository;
    private final FileRepository fileRepository;
    private final RetailOperationWithPointsMapper retailOperationWithPointsMapper;

    @Override
    public List<RetailOperationWithPointsDto> getAll() {
        return retailOperationWithPointsRepository.findAll().stream()
                .map(retailOperationWithPointsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailOperationWithPointsDto getById(Long id) {
        Optional<RetailOperationWithPoints> retailOperationWithPoints = retailOperationWithPointsRepository.findById(id);
        return retailOperationWithPointsMapper.toDto(
                retailOperationWithPoints.orElse(new RetailOperationWithPoints()));
    }

    @Override
    public RetailOperationWithPointsDto create(RetailOperationWithPointsDto dto) {

        LocalDateTime currentTime = LocalDateTime.parse(dto.getCurrentTime().replace("T", " "),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        LocalDateTime accrualDate = LocalDateTime.parse(dto.getAccrualDate().replace("T", " "),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        RetailOperationWithPoints retailOperationWithPoints = retailOperationWithPointsMapper.toModel(dto);
        BonusProgram bonusProgram = bonusProgramRepository.getOne(dto.getBonusProgramId());
        Contractor contractor = contractorRepository.getOne(dto.getContractorId());
        Task task = taskRepository.getOne(dto.getTaskId());
        List<File> fileList = dto.getFileIds().stream()
                .map(fileRepository::getOne)
                .collect(Collectors.toList());

        retailOperationWithPoints.setAccrualDate(accrualDate);
        retailOperationWithPoints.setCurrentTime(currentTime);
        retailOperationWithPoints.setBonusProgram(bonusProgram);
        retailOperationWithPoints.setContractor(contractor);
        retailOperationWithPoints.setTask(task);
        retailOperationWithPoints.setFiles(fileList);

        return retailOperationWithPointsMapper.toDto(retailOperationWithPointsRepository.save(retailOperationWithPoints));
    }

    @Override
    public RetailOperationWithPointsDto update(RetailOperationWithPointsDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailOperationWithPointsRepository.deleteById(id);
    }
}
