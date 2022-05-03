package src.main.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.entity.production.TechnicalOperations;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.production.TechnicalOperationsDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.production.TechnicalCardRepository;
import com.trade_accounting.repositories.production.TechnicalOperationsRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.production.TechnicalOperationsService;
import com.trade_accounting.utils.mapper.production.TechnicalOperationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicalOperationsServiceImpl implements TechnicalOperationsService {

    private final TechnicalOperationsRepository technicalOperationsRepository;
    private final TechnicalOperationsMapper technicalOperationsMapper;
    private final TechnicalCardRepository technicalCardRepository;
    private final WarehouseRepository warehouseRepository;
    private final CompanyRepository companyRepository;

//    @Override
//    public List<TechnicalOperationsDto> search(Specification<TechnicalOperations> spec) {
//        return executeSearch(technicalOperationsRepository, technicalOperationsMapper::toDto, spec);
//    }

    @Override
    public List<TechnicalOperationsDto> getAll() {
        return technicalOperationsRepository.findAll().stream()
                .map(technicalOperationsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalOperationsDto getById(Long id) {
        return technicalOperationsMapper.toDto(technicalOperationsRepository.getOne(id));
    }

    @Override
    public TechnicalOperationsDto create(TechnicalOperationsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public TechnicalOperationsDto update(TechnicalOperationsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        technicalOperationsRepository.deleteById(id);

    }

    @Override
    public List<TechnicalOperationsDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<TechnicalOperations> all = technicalOperationsRepository.findAll();
            return all.stream().map(technicalOperationsMapper::toDto).collect(Collectors.toList());
        } else {
            List<TechnicalOperations> list = technicalOperationsRepository.search(searchTerm);
            return list.stream().map(technicalOperationsMapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public void moveToRecyclebin(long id) {
        TechnicalOperations technicalOperations = technicalOperationsRepository.getOne(id);
        technicalOperations.setIsRecyclebin(true);
        technicalOperationsRepository.save(technicalOperations);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        TechnicalOperations technicalOperations = technicalOperationsRepository.getOne(id);
        technicalOperations.setIsRecyclebin(false);
        technicalOperationsRepository.save(technicalOperations);
    }


    private TechnicalOperationsDto saveOrUpdate(TechnicalOperationsDto dto) {
        TechnicalOperations technicalOperations = new TechnicalOperations();

        TechnicalCard technicalCard = technicalCardRepository.getTechnicalCardById(dto.getTechnicalCard());
        Warehouse warehouse = warehouseRepository.getWarehouseById(dto.getWarehouse());
        LocalDateTime dateOperation = LocalDateTime.parse(dto.getDate().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        technicalOperations.setTechnicalCard(technicalCard);
        technicalOperations.setWarehouse(warehouse);
        technicalOperations.setDate(dateOperation);

        technicalOperations.setId(dto.getId());
        technicalOperations.setVolume(dto.getVolume());
        technicalOperations.setComment(dto.getComment());
        technicalOperations.setIsPrint(dto.getIsPrint());
        technicalOperations.setIsSent(dto.getIsSent());
        technicalOperations.setCompany(companyRepository.getCompaniesById(dto.getCompanyId()));

        return technicalOperationsMapper.toDto(technicalOperationsRepository.save(technicalOperations));
    }

    @Override
    public List<TechnicalOperationsDto> search(Specification<TechnicalOperations> spec) {
        List<TechnicalOperations> technicalOperationsList = technicalOperationsRepository.findAll(spec);

        List<TechnicalOperationsDto> technicalOperationsDtoList = new ArrayList<>();
        for(TechnicalOperations io : technicalOperationsList) {
            technicalOperationsDtoList.add(technicalOperationsMapper.toDto(io));
        }
        return technicalOperationsDtoList;
    }
}
