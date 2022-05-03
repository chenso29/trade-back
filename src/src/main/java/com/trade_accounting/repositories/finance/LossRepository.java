package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.Loss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LossRepository extends JpaRepository<Loss,Long>, JpaSpecificationExecutor<Loss> {
}
