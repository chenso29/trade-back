package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.AttributeOfCalculationObject;
import com.trade_accounting.models.dto.warehouse.AttributeOfCalculationObjectDto;
import com.trade_accounting.repositories.warehouse.AttributeOfCalculationObjectRepository;
import com.trade_accounting.services.interfaces.warehouse.AttributeOfCalculationObjectService;
import com.trade_accounting.utils.mapper.warehouse.AttributeOfCalculationObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AttributeOfCalculationObjectServiceImpl implements AttributeOfCalculationObjectService {

    private final AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;

    private final AttributeOfCalculationObjectMapper attributeOfCalculationObjectMapper;

    @Override
    public List<AttributeOfCalculationObjectDto> getAll() {
        return attributeOfCalculationObjectRepository.findAll().stream()
                .map(attributeOfCalculationObjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttributeOfCalculationObjectDto getById(Long id) {
        return attributeOfCalculationObjectMapper.toDto(
                attributeOfCalculationObjectRepository.findById(id).orElse(new AttributeOfCalculationObject())
        );
    }

    @Override
    public AttributeOfCalculationObjectDto create(AttributeOfCalculationObjectDto attribute) {
        AttributeOfCalculationObject savedAttribute = attributeOfCalculationObjectRepository.save(
                attributeOfCalculationObjectMapper.toModel(attribute)
        );

        return attributeOfCalculationObjectMapper.toDto(
                savedAttribute
        );
    }

    @Override
    public AttributeOfCalculationObjectDto update(AttributeOfCalculationObjectDto attribute) {
        return create(attribute);
    }

    @Override
    public void deleteById(Long id) {
        attributeOfCalculationObjectRepository.deleteById(id);
    }
}
