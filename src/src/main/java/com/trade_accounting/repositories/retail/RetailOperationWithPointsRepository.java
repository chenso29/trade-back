package src.main.java.com.trade_accounting.repositories.retail;

import com.trade_accounting.models.entity.retail.RetailOperationWithPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailOperationWithPointsRepository extends JpaRepository<RetailOperationWithPoints,Long> {
}
