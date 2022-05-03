package src.main.java.com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesSubGoodsForSaleRepository extends JpaRepository<SalesSubGoodsForSale, Long>,
        JpaSpecificationExecutor<SalesSubGoodsForSale> {

    @Query("from SalesSubGoodsForSale s" +
            " where lower(concat(s.id, ' '))" +
            " like lower(concat('%', :req, '%'))")
    List<SalesSubGoodsForSale> searchString(@Param("req") String request);
}
