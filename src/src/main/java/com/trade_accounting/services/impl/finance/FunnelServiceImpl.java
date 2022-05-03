package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.dto.company.ContractorDto;
import com.trade_accounting.models.dto.company.ContractorStatusDto;
import com.trade_accounting.models.dto.finance.FunnelDto;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import com.trade_accounting.models.entity.finance.Funnel;
import com.trade_accounting.repositories.finance.FunnelRepository;
import com.trade_accounting.repositories.invoice.InvoiceRepository;
import com.trade_accounting.services.interfaces.company.ContractorService;
import com.trade_accounting.services.interfaces.company.ContractorStatusService;
import com.trade_accounting.services.interfaces.finance.FunnelService;
import com.trade_accounting.services.interfaces.invoice.InvoiceProductService;
import com.trade_accounting.services.interfaces.invoice.InvoiceService;
import com.trade_accounting.services.interfaces.invoice.InvoicesStatusService;
import com.trade_accounting.utils.mapper.finance.FunnelMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FunnelServiceImpl implements FunnelService {

    private final InvoiceRepository invoiceRepository;
    private final FunnelRepository funnelRepository;
    private final FunnelMapper funnelMapper;
    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ContractorService contractorService;
    private final InvoicesStatusService invoicesStatusService;
    private final ContractorStatusService contractorStatusService;


    @Override
    public List<FunnelDto> getAll() {
        List<FunnelDto> funnelDtoList = new ArrayList<>();
        funnelDtoList.add(new FunnelDto(1L, invoicesStatusService.getById(1L).getStatusName(), countByInvoiceStatusId(1L), getOrdersTime(1L), "", getOrdersPrice(1L), "invoice"));
        funnelDtoList.add(new FunnelDto(2L, invoicesStatusService.getById(2L).getStatusName(), countByInvoiceStatusId(2L), getOrdersTime(2L), calcOrdersConversion(2L), getOrdersPrice(2L), "invoice"));
        funnelDtoList.add(new FunnelDto(3L, invoicesStatusService.getById(3L).getStatusName(), countByInvoiceStatusId(3L), getOrdersTime(3L), calcOrdersConversion(3L), getOrdersPrice(3L), "invoice"));
        funnelDtoList.add(new FunnelDto(4L, invoicesStatusService.getById(4L).getStatusName(), countByInvoiceStatusId(4L), getOrdersTime(4L), calcOrdersConversion(4L), getOrdersPrice(4L), "invoice"));
        funnelDtoList.add(new FunnelDto(5L, contractorStatusService.getById(1L).getName(), countByContractorStatusId(1L), "", "contractor"));
        funnelDtoList.add(new FunnelDto(6L, contractorStatusService.getById(2L).getName(), countByContractorStatusId(2L), calcContractorsConversion(2L), "contractor"));
        funnelDtoList.add(new FunnelDto(7L, contractorStatusService.getById(3L).getName(), countByContractorStatusId(3L), calcContractorsConversion(3L), "contractor"));
        funnelDtoList.add(new FunnelDto(8L, contractorStatusService.getById(4L).getName(), countByContractorStatusId(4L), calcContractorsConversion(4L), "contractor"));
        funnelDtoList.add(new FunnelDto(9L, contractorStatusService.getById(5L).getName(), countByContractorStatusId(5L), calcContractorsConversion(5L), "contractor"));
        Long i = 0L;
        for (FunnelDto funnelDto : funnelDtoList) {
            if (getById(i) != null) {
                create(funnelDto);
                i++;
            }
        }
        return funnelDtoList;
    }


    @Override
    public FunnelDto getById(Long id) {
        return funnelMapper.toDto(funnelRepository.findById(id).orElse(new Funnel()));
    }

    @Override
    public FunnelDto create(FunnelDto dto) {
        Funnel funnel = funnelRepository.save(funnelMapper.toModel(dto));
        dto.setId(funnel.getId());
        return funnelMapper.toDto(funnel);
    }

    @Override
    public FunnelDto update(FunnelDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        funnelRepository.deleteById(id);
    }

    @Override
    public List<FunnelDto> search(Specification<Funnel> spec) {
        return executeSearch(funnelRepository, funnelMapper::toDto, spec);
    }

    ///////
    public Long countByInvoiceStatusId(Long statusId) {
        Long i = 0L;
        for (InvoiceDto dto : invoiceRepository.getAll()) {
            if (Objects.equals(dto.getInvoicesStatusId(), statusId)) {
                i++;
            }
        }
        return i;
    }

    private String getOrdersTime(Long id) {
        List<InvoiceDto> list = invoiceService.getAll();
        Long alltime = 0L;
        for (InvoiceDto invoiceDto : list) {
            if (Objects.equals(invoiceDto.getInvoicesStatusId(), id)) {
                LocalDateTime startTime = LocalDateTime.parse(invoiceService.getById(invoiceDto.getId()).getDate());
                LocalDateTime endTime = LocalDateTime.now();
                Long invoiceDuration = Duration.between(startTime, endTime).toMillis();
                alltime += invoiceDuration;
                alltime /= countByInvoiceStatusId(id);
            }
        }
        return String.format("%02dд %02dч %02dм %02dс",
                TimeUnit.MILLISECONDS.toDays(alltime),
                TimeUnit.MILLISECONDS.toHours(alltime) -
                        TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(alltime)),
                TimeUnit.MILLISECONDS.toMinutes(alltime) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(alltime)),
                TimeUnit.MILLISECONDS.toSeconds(alltime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(alltime)));
    }

    public String calcOrdersConversion(Long statusId) {
        double res = 0;
        if (statusId == 1L) {
            return "";
        } else if (statusId > 1L && countByInvoiceStatusId(statusId) > 0) {
            res = ((double) countByInvoiceStatusId(statusId) / countByInvoiceStatusId(statusId - 1) * 100);
        }
        return String.format("%s%%", res);
    }

    private String getOrdersPrice(Long statusId) {
        List<InvoiceProductDto> invoiceProductDtoList = invoiceProductService.getAll();
        List<InvoiceDto> invoiceDtoList = invoiceService.getAll();
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);
        for (InvoiceDto invoiceDto : invoiceDtoList) {
            for (InvoiceProductDto invoiceProductDto : invoiceProductDtoList) {
                if (Objects.equals(invoiceProductDto.getInvoiceId(), invoiceDto.getId()) &&
                        Objects.equals(invoiceDto.getInvoicesStatusId(), statusId))
                    totalPrice = totalPrice.add(invoiceProductDto.getPrice()
                            .multiply(invoiceProductDto.getAmount()));
            }
        }
        return String.format("%.2f", totalPrice);
    }

    public Long countByContractorStatusId(Long statusId) {
        Long i = 0L;
        for (ContractorDto dto : contractorService.getAll()) {
            if (Objects.equals(dto.getContractorStatusId(), statusId)) {
                i++;
            }
        }
        return i;
    }

    public String calcContractorsConversion(Long statusId) {
        double res = 0;
        if (statusId == 1L) {
            return "";
        } else if (statusId > 1L && countByContractorStatusId(statusId) > 0) {
            res = ((double) countByContractorStatusId(statusId) / countByContractorStatusId(statusId - 1) * 100);
        }
        return String.format("%s%%", res);
    }

}
