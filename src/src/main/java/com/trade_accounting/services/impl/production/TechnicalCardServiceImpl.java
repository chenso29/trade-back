package src.main.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.dto.production.TechnicalCardDto;
import com.trade_accounting.repositories.production.TechnicalCardRepository;
import com.trade_accounting.services.interfaces.production.TechnicalCardGroupService;
import com.trade_accounting.services.interfaces.production.TechnicalCardProductionService;
import com.trade_accounting.services.interfaces.production.TechnicalCardService;
import com.trade_accounting.utils.mapper.production.TechnicalCardGroupMapper;
import com.trade_accounting.utils.mapper.production.TechnicalCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicalCardServiceImpl implements TechnicalCardService {

    private final TechnicalCardRepository technicalCardRepository;
    private final TechnicalCardProductionService cardProductionService;
    private final TechnicalCardMapper technicalCardMapper;
    private final TechnicalCardGroupMapper technicalCardGroupMapper;
    private final TechnicalCardGroupService technicalCardGroupService;

    @Override
    public List<TechnicalCardDto> getAll() {
        return technicalCardRepository.findAll().stream()
                .map(technicalCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalCardDto getById(Long id) {
        return technicalCardMapper.toDto(
                technicalCardRepository.getOne(id));
    }

    @Override
    public TechnicalCardDto create(TechnicalCardDto dto) {
        TechnicalCard technicalCard = technicalCardMapper.toModel(dto);

        technicalCard.setTechnicalCardGroup(
                technicalCardGroupMapper.toModel(technicalCardGroupService
                        .getById(dto.getTechnicalCardGroupId())
                )
        );

        technicalCard.setFinalProduction(
                cardProductionService.finaAllById(dto.getFinalProductionId())
        );

        technicalCard.setMaterials(
                cardProductionService.finaAllById(dto.getFinalProductionId())
        );

        return technicalCardMapper.toDto(technicalCardRepository.save(technicalCard));
    }

    @Override
    public TechnicalCardDto update(TechnicalCardDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        technicalCardRepository.deleteById(id);
    }

    @Override
    public List<TechnicalCardDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            return technicalCardRepository.findAll().stream()
                    .map(technicalCardMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return technicalCardRepository.search(searchTerm).stream()
                    .map(technicalCardMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<TechnicalCardDto> getAllByTechnicalCardGroupId(Long id) {
        return technicalCardRepository.getAllByTechnicalCardGroupId(id).stream()
                .map(technicalCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TechnicalCardDto> search(Specification<TechnicalCard> spec) {
        return executeSearch(technicalCardRepository, technicalCardMapper::toDto, spec);
    }

}
