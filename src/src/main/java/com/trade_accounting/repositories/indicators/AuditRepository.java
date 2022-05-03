package src.main.java.com.trade_accounting.repositories.indicators;

import com.trade_accounting.models.entity.indicators.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long>, JpaSpecificationExecutor<Audit> {

    @Query("from Audit s" +
            " where lower(concat(s.id, ' '))" +
            " like lower(concat('%', :req, '%'))")
    List<Audit> searchString(@Param("req") String request);

    @Query("from Audit e " +
            "where lower ( concat(e.description, ' ', e.date)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<Audit> getBySearch(@Param("symbols") String search);

}
