package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.AgentReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentReportsRepository extends JpaRepository<AgentReports, Long> {

}
