package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.TypeOfPrice;
import com.trade_accounting.models.dto.company.TypeOfPriceDto;
import com.trade_accounting.repositories.company.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.company.TypeOfPriceService;
import com.trade_accounting.utils.mapper.company.TypeOfPriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeOfPriceServiceImpl implements TypeOfPriceService {

    private final TypeOfPriceRepository typeOfPriceRepository;

    private final TypeOfPriceMapper typeOfPriceMapper;

    @Override
    public List<TypeOfPriceDto> getAll() {
        return typeOfPriceRepository.findAll().stream()
                .map(typeOfPriceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeOfPriceDto getById(Long id) {
        return typeOfPriceMapper.toDto(
                typeOfPriceRepository.findById(id).orElse(new TypeOfPrice())
        );
    }

    @Override
    public TypeOfPriceDto create(TypeOfPriceDto typeOfPriceDto) {
        TypeOfPrice savedTypeOfPrice = typeOfPriceRepository.save(
                typeOfPriceMapper.toModel(typeOfPriceDto)
        );
        typeOfPriceDto.setId(savedTypeOfPrice.getId());
        return typeOfPriceMapper.toDto(savedTypeOfPrice);
    }


    @Override
    public TypeOfPriceDto update(TypeOfPriceDto typeOfPriceDto) {
        return create(typeOfPriceDto);
    }

    @Override
    public void deleteById(Long id) {
        typeOfPriceRepository.deleteById(id);
    }
}
