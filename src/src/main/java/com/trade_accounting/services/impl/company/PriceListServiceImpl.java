package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.PriceListRepository;
import com.trade_accounting.services.interfaces.company.PriceListService;
import com.trade_accounting.utils.mapper.company.PriceListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepository priceListRepository;
    private final CompanyRepository companyRepository;
    private final PriceListMapper priceListMapper;

    @Override
    public List<PriceListDto> getAll() {
        return priceListRepository.findAll().stream()
                .map(priceListMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PriceListDto getById(Long id) {
        PriceList priceList = priceListRepository.getOne(id);
        return priceListMapper.toDto(priceList);
    }

    /**
     * @changed by Pavel Andrusov
     */
    @Override
    public PriceListDto create(PriceListDto dto) {
        LocalDateTime time = LocalDateTime.parse(dto.getTime().replace("T"," ")
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        PriceList priceList = priceListMapper.toModel(dto);
        priceList.setCompany(companyRepository.getOne(dto.getCompanyId()));
        priceList.setTime(time);
        return priceListMapper.toDto(priceListRepository.save(priceList));
    }

    @Override
    public PriceListDto update(PriceListDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        priceListRepository.deleteById(id);
    }
}
