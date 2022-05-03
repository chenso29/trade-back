package src.test.java.com.trade_accounting.Stubs.model.finance;

import com.trade_accounting.models.entity.finance.AgentReports;

import java.time.LocalDateTime;

import static com.trade_accounting.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.Stubs.ModelStubs.getContractor;

public class AgentReportsModelStubs {
    public static AgentReports getAgentReports(Long id) {
        return AgentReports.builder()
                .id(id)
                .documentType(".odt")
                .number("1")
                .time(LocalDateTime.now())
                .company(getCompany(1L))
                .contractor(getContractor(1L))
                .sum(1L)
                .remunirationSum(1L)
                .comitentSum(1L)
                .paid(1L)
                .status("Status 1")
                .sent(1L)
                .printed(1L)
                .commentary("Comment 1")
                .build();
    }
}
