package src.main.java.com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.TechnicalCardGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalCardGroupRepository extends JpaRepository<TechnicalCardGroup, Long> {
}
