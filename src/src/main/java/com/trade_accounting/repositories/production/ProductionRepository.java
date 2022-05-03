package src.main.java.com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Production, Long> {

}
