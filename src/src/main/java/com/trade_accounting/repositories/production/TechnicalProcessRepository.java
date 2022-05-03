package src.main.java.com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.TechnicalProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface TechnicalProcessRepository extends JpaRepository<TechnicalProcess, Long>, JpaSpecificationExecutor<TechnicalProcess> {

    Optional<TechnicalProcess> getByName(@NotNull String name);

    @Query("FROM TechnicalProcess techProcess WHERE LOWER(CONCAT(techProcess.name, ' ', techProcess.description))" +
            " LIKE LOWER(CONCAT('%', :req, '%'))")
    List<TechnicalProcess> search(@Param("req") String request);

    List<TechnicalProcess> findByNameContainingOrDescriptionContaining(@NotNull String name, String description);
}
