package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.Payment;
import com.trade_accounting.models.dto.finance.PaymentDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PaymentService extends AbstractService<PaymentDto> {

    List<PaymentDto> filter(Specification<Payment> specification);

    List<PaymentDto> search(String search);

    void moveToRecyclebin(long id);

    void restoreFromRecyclebin(long id);
}
