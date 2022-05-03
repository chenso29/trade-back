package src.main.java.com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.RequestsProductions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RequestsProductionsRepository extends JpaRepository<RequestsProductions, Long>,
        JpaSpecificationExecutor<RequestsProductions> {
}
