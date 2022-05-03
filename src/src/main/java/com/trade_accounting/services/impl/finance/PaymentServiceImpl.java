package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.Payment;
import com.trade_accounting.models.dto.finance.PaymentDto;
import com.trade_accounting.repositories.finance.PaymentRepository;
import com.trade_accounting.services.interfaces.finance.PaymentService;
import com.trade_accounting.utils.mapper.finance.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    @Override
    public List<PaymentDto> getAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentMapper.toDto(
                paymentRepository.findById(id).orElse(new Payment())
        );
    }

    @Override
    public PaymentDto create(PaymentDto paymentDto) {
        Payment payment = paymentRepository.save(paymentMapper.toModel(paymentDto));
        paymentDto.setId(payment.getId());
        return paymentDto;
    }


    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        return create(paymentDto);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentDto> search(String search) {
        return paymentRepository.search(search).stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void moveToRecyclebin(long id) {
        Payment payment = paymentRepository.getOne(id);
        payment.setIsRecyclebin(true);
        paymentRepository.save(payment);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        Payment payment = paymentRepository.getOne(id);
        payment.setIsRecyclebin(false);
        paymentRepository.save(payment);
    }

    @Override
    public List<PaymentDto> filter(Specification<Payment> specification) {
        return paymentRepository.findAll(specification).stream().
                map(paymentMapper::toDto).collect(Collectors.toList());
    }
}
