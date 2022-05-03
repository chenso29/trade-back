package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.TypeOfContractor;
import com.trade_accounting.models.dto.company.TypeOfContractorDto;
import com.trade_accounting.repositories.company.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.company.TypeOfContractorService;
import com.trade_accounting.utils.mapper.company.TypeOfContractorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeOfContractorServiceImpl implements TypeOfContractorService {

    private final TypeOfContractorRepository typeOfContractorRepository;
    private final TypeOfContractorMapper typeOfContractorMapper;

    @Override
    public List<TypeOfContractorDto> getAll() {
        return typeOfContractorRepository.findAll().stream()
                .map(typeOfContractorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeOfContractorDto getById(Long id) {
        Optional<TypeOfContractor> typeOfContractor = typeOfContractorRepository.findById(id);
        return typeOfContractorMapper.toDto(typeOfContractor.orElse(new TypeOfContractor()));
    }

    @Override
    public TypeOfContractorDto create(TypeOfContractorDto typeOfContractorDto) {
        TypeOfContractor typeOfContractor = typeOfContractorMapper.toModel(typeOfContractorDto);
        typeOfContractorDto.setId(typeOfContractor.getId());
        return typeOfContractorMapper.toDto(
                typeOfContractorRepository.save(typeOfContractor));
    }


    @Override
    public TypeOfContractorDto update(TypeOfContractorDto typeOfContractorDto) {
        return create(typeOfContractorDto);
    }

    @Override
    public void deleteById(Long id) {
        typeOfContractorRepository.deleteById(id);
    }

    @Override
    public TypeOfContractorDto getByName(String name) {
        Optional<TypeOfContractor> typeOfContractor = typeOfContractorRepository.findByName(name);
        return typeOfContractorMapper.toDto(typeOfContractor.orElse(new TypeOfContractor()));
    }
}
