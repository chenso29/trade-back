package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.MutualSettlements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MutualSettlementsRepository extends JpaRepository<MutualSettlements, Long>, JpaSpecificationExecutor<MutualSettlements> {

}
