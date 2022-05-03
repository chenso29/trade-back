package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.Correction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CorrectionRepository extends JpaRepository<Correction, Long>,
        JpaSpecificationExecutor<Correction> {

    @Query("SELECT c FROM Correction c")
    List<Correction> getAll();

    @Query("SELECT c FROM Correction c WHERE c.id = :id")
    Correction getCorrectionById(@Param("id") Long id);
}

