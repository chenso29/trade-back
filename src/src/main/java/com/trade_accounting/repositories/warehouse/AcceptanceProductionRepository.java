package src.main.java.com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcceptanceProductionRepository extends JpaRepository<AcceptanceProduction, Long>,
        JpaSpecificationExecutor<AcceptanceProduction> {
}
