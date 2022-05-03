package src.main.java.com.trade_accounting.repositories.fias;

import com.trade_accounting.models.entity.fias.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
