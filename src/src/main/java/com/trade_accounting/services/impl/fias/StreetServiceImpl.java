package src.main.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.StreetDto;
import com.trade_accounting.models.entity.fias.Street;
import com.trade_accounting.repositories.fias.CityRepository;
import com.trade_accounting.repositories.fias.StreetRepository;
import com.trade_accounting.services.interfaces.fias.StreetService;
import com.trade_accounting.utils.mapper.fias.StreetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreetServiceImpl implements StreetService {
    private final StreetMapper streetMapper;
    private final StreetRepository streetRepository;
    private final CityRepository cityRepository;

    @Override
    public List<StreetDto> getAll() {
        List<Street> all = streetRepository.findAll();
        return all.stream().map(streetMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StreetDto getById(Long id) {
        Street street = streetRepository.findById(id).orElse(new Street());
        return streetMapper.toDto(street);
    }

    @Override
    public StreetDto create(StreetDto streetDto) {
        Street street = streetMapper.toModel(streetDto);
        Street save = streetRepository.save(street);
        return streetMapper.toDto(save);
    }

    @Override
    public StreetDto update(StreetDto streetDto) {
        Street street = streetRepository.save(streetMapper.toModel(streetDto));
        return streetMapper.toDto(street);
    }

    @Override
    public void deleteById(Long id) {
        streetRepository.deleteById(id);
    }
}
