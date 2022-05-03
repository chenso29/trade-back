package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.LegalDetail;
import com.trade_accounting.models.dto.company.LegalDetailDto;
import com.trade_accounting.repositories.company.AddressRepository;
import com.trade_accounting.repositories.company.LegalDetailRepository;
import com.trade_accounting.repositories.company.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.company.LegalDetailService;
import com.trade_accounting.utils.mapper.company.LegalDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LegalDetailServiceImpl implements LegalDetailService {

    private final LegalDetailRepository legalDetailRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final AddressRepository addressRepository;
    private final LegalDetailMapper legalDetailMapper;

    @Override
    public List<LegalDetailDto> getAll() {
        return legalDetailRepository.findAll().stream()
                .map(legalDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LegalDetailDto getById(Long id) {
        return legalDetailMapper.toDto(
                legalDetailRepository.findById(id).orElse(new LegalDetail())
        );
    }

    @Override
    public LegalDetailDto create(LegalDetailDto legalDetailDto) {
        LegalDetail legalDetail = legalDetailMapper.toModel(legalDetailDto);

        legalDetail.setAddress(addressRepository.getOne(
                legalDetailDto.getAddressDtoId()
        ));

//        legalDetail.setTypeOfContractor(
//                typeOfContractorRepository.findByName(
//                        legalDetailDto.getTypeOfContractorDto()
//                ).orElse(null)
//        );

        return legalDetailMapper.toDto(
                legalDetailRepository.save(legalDetail)
        );
    }


    @Override
    public LegalDetailDto update(LegalDetailDto legalDetailDto) {
        LegalDetail legalDetail = legalDetailMapper.toModel(legalDetailDto);

        legalDetail.setAddress(addressRepository.getOne(legalDetailDto.getAddressDtoId()));

//        legalDetail.setTypeOfContractor(
//                typeOfContractorRepository.findByName(
//                        legalDetailDto.getTypeOfContractorDto()
//                ).orElse(null)
//        );

        return legalDetailMapper.toDto(
                legalDetailRepository.save(legalDetail)
        );
    }

    @Override
    public void deleteById(Long id) {
        legalDetailRepository.deleteById(id);
    }

}
