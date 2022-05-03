package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrepaymentReturnRepository extends JpaRepository<PrepaymentReturn, Long>, JpaSpecificationExecutor<PrepaymentReturn> {
}
