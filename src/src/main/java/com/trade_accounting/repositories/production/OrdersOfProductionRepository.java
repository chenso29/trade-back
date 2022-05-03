package src.main.java.com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.OrdersOfProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersOfProductionRepository extends JpaRepository<OrdersOfProduction, Long>, JpaSpecificationExecutor<OrdersOfProduction> {

    @Query("from OrdersOfProduction t" +
            " where lower(concat(t.id, ' ', t.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<OrdersOfProduction> search(@Param("req") String request);
}
