package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.Payment;
import com.trade_accounting.models.dto.finance.PaymentDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.finance.PaymentRepository;
import com.trade_accounting.repositories.util.ProjectRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.finance.PaymentDtoStubs;
import com.trade_accounting.utils.mapper.finance.PaymentMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ContractorRepository contractorRepository;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Spy
    private PaymentMapperImpl paymentMapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    //Tests
    @Test
    void getAll_shouldReturnListFilledPaymentDto() {
        when(paymentRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getPayment(1L),
                                ModelStubs.getPayment(2L),
                                ModelStubs.getPayment(3L)
                        ).collect(Collectors.toList())
                );

        List<PaymentDto> payments = paymentService.getAll();

        assertNotNull(payments, "Failure - expected that list of payments not null");
        assertTrue(payments.size() > 0, "Failure - expected that size of list of payments greater than 0");

        for (PaymentDto payment : payments) {
            paymentDtoIsCorrectlyInited(payment);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListPaymentDto() {
        when(paymentRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<PaymentDto> payments = paymentService.getAll();


        assertNotNull(payments, "Failure - expected that list of payments not null");
        assertEquals(0, payments.size(), "Failure - expected that size of list of payments equals 0");
    }

    @Test
    void getById_shouldReturnFilledPaymentDto() {
        Optional<Payment> paymentFromRepo = Optional.of(ModelStubs.getPayment(1L));

        when(paymentRepository.findById(1L))
                .thenReturn(paymentFromRepo);

        PaymentDto payment = paymentService.getById(1L);

        assertNotNull(payment, "failure - expected that payment not null.");
        paymentDtoIsCorrectlyInited(payment);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        paymentService.create(
                PaymentDtoStubs.getPaymentDto(1L)
        );

        verify(paymentRepository).save(any(Payment.class));
        verify(companyRepository).findById(anyLong());
        verify(contractorRepository).findById(anyLong());
        verify(contractRepository).findById(anyLong());
        verify(projectRepository).findById(anyLong());
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        paymentService.update(
                PaymentDtoStubs.getPaymentDto(1L)
        );

        verify(paymentRepository).save(any(Payment.class));
        verify(companyRepository).findById(anyLong());
        verify(contractorRepository).findById(anyLong());
        verify(contractRepository).findById(anyLong());
        verify(projectRepository).findById(anyLong());
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        paymentService.deleteById(1L);
        verify(paymentRepository).deleteById(1L);
    }

    void paymentDtoIsCorrectlyInited(PaymentDto payment) {
        assertNotNull(payment, "Fail in passed payment");
        assertNotNull(payment.getId(), "Fail in field 'id' of payment");
        assertNotNull(payment.getNumber(), "Fail in field 'number' of payment");
        assertNotNull(payment.getCompanyId(), "Fail in field 'companyDto' of payment");
        assertNotNull(payment.getContractorId(), "Fail in field 'contractorDto' of payment");
    }
}