package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.dto.finance.MoneySubCashFlowDto;
import com.trade_accounting.models.dto.finance.MoneySubProfitLossDto;

import java.time.LocalDate;


public interface MoneySubProfitLossService {
    MoneySubProfitLossDto getMoneySubProfitLossDto();

    MoneySubProfitLossDto filter(LocalDate startDatePeriod, LocalDate endDatePeriod, Long companyId);
}
