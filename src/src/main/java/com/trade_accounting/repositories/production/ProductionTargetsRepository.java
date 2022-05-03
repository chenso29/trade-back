package src.main.java.com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.ProductionTargets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionTargetsRepository extends JpaRepository<ProductionTargets, Long>,
        JpaSpecificationExecutor<ProductionTargets> {

    @Query("from ProductionTargets t" +
            " where lower(concat(t.id, ' ', t.description))" +
            " like lower(concat('%', :req, '%'))")
    List<ProductionTargets> search(@Param("req") String request);

}