package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.Prepayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrepayoutRepository extends JpaRepository<Prepayout, Long>, JpaSpecificationExecutor<Prepayout> {
}
