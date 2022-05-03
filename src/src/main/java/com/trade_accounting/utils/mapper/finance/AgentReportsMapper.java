package src.main.java.com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.AgentReports;
import com.trade_accounting.models.dto.finance.AgentReportsDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

/**
 * @author Pavel Andrusov
 * @since 20.07.2021
 */

@Mapper(componentModel = "spring")
public interface AgentReportsMapper  {
    default AgentReports toModel(AgentReportsDto agentReportsDto) {
        if (agentReportsDto == null) {
            return null;
        }

        return AgentReports.builder()
                .id(agentReportsDto.getId())
                .documentType(agentReportsDto.getDocumentType())
                .number(agentReportsDto.getNumber())
                .sum(agentReportsDto.getSum())
                .remunirationSum(agentReportsDto.getRemunirationSum())
                .comitentSum(agentReportsDto.getComitentSum())
                .paid(agentReportsDto.getPaid())
                .status(agentReportsDto.getStatus())
                .sent(agentReportsDto.getSent())
                .printed(agentReportsDto.getPrinted())
                .commentary(agentReportsDto.getCommentary())
                .build();
    }

    default AgentReportsDto toDto(AgentReports agentReports) {
        AgentReportsDto agentReportsDto = new AgentReportsDto();
        if (agentReports == null) {
            return null;
        }

        agentReportsDto.setId(agentReports.getId());
        agentReportsDto.setDocumentType(agentReports.getDocumentType());
        agentReportsDto.setNumber(agentReports.getNumber());
        agentReportsDto.setTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(agentReports.getTime()));
        agentReportsDto.setCompanyId(agentReports.getCompany().getId());
        agentReportsDto.setContractorId(agentReports.getContractor().getId());
        agentReportsDto.setSum(agentReports.getSum());
        agentReportsDto.setRemunirationSum(agentReports.getRemunirationSum());
        agentReportsDto.setComitentSum(agentReports.getComitentSum());
        agentReportsDto.setPaid(agentReports.getPaid());
        agentReportsDto.setStatus(agentReports.getStatus());
        agentReportsDto.setSent(agentReports.getSent());
        agentReportsDto.setPrinted(agentReports.getPrinted());
        agentReportsDto.setCommentary(agentReports.getCommentary());

        return agentReportsDto;
    }
}
