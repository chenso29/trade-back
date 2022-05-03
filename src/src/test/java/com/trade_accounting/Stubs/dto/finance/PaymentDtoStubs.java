package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.models.dto.finance.PaymentDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.finance.PaymentMapper;
import org.mapstruct.factory.Mappers;

public class PaymentDtoStubs {
    private static final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);
    public static PaymentDto getPaymentDto(Long id) {
        return mapper.toDto(ModelStubs.getPayment(id));
    }
}
