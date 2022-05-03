package src.main.java.com.trade_accounting.repositories.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseForecastRepository extends JpaRepository<PurchaseForecast, Long> {

    @Query("SELECT f FROM PurchaseForecast f")
    List<PurchaseForecast> findAllBy();

    @Query("SELECT f FROM PurchaseForecast f WHERE f.id = :id")
    PurchaseForecast getOne(@Param("id") Long id);
}
