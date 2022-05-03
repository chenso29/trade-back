package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Revenue;
import com.trade_accounting.models.dto.warehouse.RevenueDto;
import com.trade_accounting.repositories.warehouse.AcceptanceProductionRepository;
import com.trade_accounting.repositories.warehouse.AcceptanceRepository;
import com.trade_accounting.repositories.invoice.InvoiceProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.RevenueRepository;
import com.trade_accounting.services.interfaces.warehouse.RevenueService;
import com.trade_accounting.utils.mapper.warehouse.RevenueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService {

	private final RevenueRepository revenueRepository;
	private final RevenueMapper revenueMapper;
	private final ProductRepository productRepository;
	private final AcceptanceRepository acceptanceRepository;
	private final AcceptanceProductionRepository acceptanceProductionRepository;
	private final InvoiceProductRepository invoiceProductRepository;

	@Override
	public List<RevenueDto> getAll() {
		return revenueRepository.findAll().stream()
				.map(revenueMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public RevenueDto getById(Long id) {
		return revenueMapper.toDto(revenueRepository.getOne(id));
	}

	@Override
	public RevenueDto create(RevenueDto dto) {
		Revenue revenue = revenueMapper.toModel(dto);
		revenue.setProduct(productRepository.getOne(dto.getProductId()));
		revenue.setAcceptance(acceptanceRepository.getOne(dto.getAcceptanceId()));
		revenue.setAcceptanceProduction(acceptanceProductionRepository.getOne(dto.getAcceptanceProductionId()));
		revenue.setInvoiceProduct(invoiceProductRepository.getOne(dto.getInvoiceProductId()));
		return revenueMapper.toDto(revenueRepository.save(revenue));
	}

	@Override
	public RevenueDto update(RevenueDto dto) {
		return create(dto);
	}

	@Override
	public void deleteById(Long id) {
		revenueRepository.deleteById(id);
	}
}
