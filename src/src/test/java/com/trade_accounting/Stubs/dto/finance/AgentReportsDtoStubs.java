package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.Stubs.model.finance.AgentReportsModelStubs;
import com.trade_accounting.models.entity.finance.AgentReports;
import com.trade_accounting.models.dto.finance.AgentReportsDto;
import com.trade_accounting.utils.mapper.finance.AgentReportsMapper;
import org.mapstruct.factory.Mappers;

public class AgentReportsDtoStubs {
    private static final AgentReportsMapper mapper = Mappers.getMapper(AgentReportsMapper.class);

    public static AgentReportsDto getDto(Long id) {
        AgentReports model = AgentReportsModelStubs.getAgentReports(id);
        return mapper.toDto(model);
    }
}
