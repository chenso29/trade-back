package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.TaxSystem;
import com.trade_accounting.models.dto.company.TaxSystemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxSystemRepository extends JpaRepository<TaxSystem, Long> {

    @Query("select new com.trade_accounting.models.dto.company.TaxSystemDto(" +
            "ts.id, " +
            "ts.name, " +
            "ts.sortNumber) from TaxSystem ts")
    List<TaxSystemDto> getAll();

    @Query("select new com.trade_accounting.models.dto.company.TaxSystemDto(" +
            "ts.id, " +
            "ts.name, " +
            "ts.sortNumber) from TaxSystem ts where ts.id = :id")
    TaxSystemDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.company.TaxSystemDto(" +
            "p.taxSystem.id, " +
            "p.taxSystem.name, " +
            "p.taxSystem.sortNumber) from Product p where p.id = :id")
    TaxSystemDto getTaxSystemById(@Param("id") Long id);
}
