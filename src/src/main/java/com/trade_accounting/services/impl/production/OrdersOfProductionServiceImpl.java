package src.main.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.production.OrdersOfProduction;
import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.dto.production.OrdersOfProductionDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.production.OrdersOfProductionRepository;
import com.trade_accounting.repositories.production.TechnicalCardRepository;
import com.trade_accounting.services.interfaces.production.OrdersOfProductionService;
import com.trade_accounting.utils.mapper.production.OrdersOfProductionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersOfProductionServiceImpl implements OrdersOfProductionService {

    private final OrdersOfProductionRepository ordersOfProductionRepository;
    private final OrdersOfProductionMapper ordersOfProductionMapper;
    private final CompanyRepository companyRepository;
    private final TechnicalCardRepository technicalCardRepository;

    @Override
    public List<OrdersOfProductionDto> search(Specification<OrdersOfProduction> spec) {
        return executeSearch(ordersOfProductionRepository, ordersOfProductionMapper::toDto, spec);
    }

    @Override
    public List<OrdersOfProductionDto> getAll() {
        return ordersOfProductionRepository.findAll().stream()
                .map(ordersOfProductionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersOfProductionDto getById(Long id) {
        return ordersOfProductionMapper.toDto(ordersOfProductionRepository.getOne(id));
    }

    @Override
    public OrdersOfProductionDto create(OrdersOfProductionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public OrdersOfProductionDto update(OrdersOfProductionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        ordersOfProductionRepository.deleteById(id);
    }

    @Override
    public List<OrdersOfProductionDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<OrdersOfProduction> all = ordersOfProductionRepository.findAll();
            return all.stream().map(ordersOfProductionMapper::toDto).collect(Collectors.toList());
        } else {
            List<OrdersOfProduction> list = ordersOfProductionRepository.search(searchTerm);
            return list.stream().map(ordersOfProductionMapper::toDto).collect(Collectors.toList());
        }
    }

    private OrdersOfProductionDto saveOrUpdate(OrdersOfProductionDto dto) {

        OrdersOfProduction ordersOfProduction = new OrdersOfProduction();

        TechnicalCard technicalCard = technicalCardRepository.getTechnicalCardById(dto.getTechnicalCardId());
        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        LocalDateTime date = LocalDateTime.parse(dto.getDate().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime PlannedProductionDate = LocalDateTime.parse(dto.getPlannedProductionDate().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        ordersOfProduction.setTechnicalCard(technicalCard);
        ordersOfProduction.setCompany(company);
        ordersOfProduction.setDate(date);
        ordersOfProduction.setPlannedProductionDate(PlannedProductionDate);

        ordersOfProduction.setId(dto.getId());
        ordersOfProduction.setComment(dto.getComment());
        ordersOfProduction.setIsPrint(dto.getIsPrint());
        ordersOfProduction.setIsSent(dto.getIsSent());
        ordersOfProduction.setProduce(dto.getProduce());
        ordersOfProduction.setVolume(dto.getVolume());

        return ordersOfProductionMapper.toDto(ordersOfProductionRepository.save(ordersOfProduction));

    }
}
