package src.main.java.com.trade_accounting.repositories.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseControlRepository extends JpaRepository<PurchaseControl, Long>, JpaSpecificationExecutor<PurchaseControl> {
    @Query("from PurchaseControl s" +
            " where lower(concat(s.id, ' '))" +
            " like lower(concat('%', :req, '%'))")
    List<PurchaseControl> search(@Param("req") String request);
}
