package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {
}
