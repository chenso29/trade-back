package src.main.java.com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.StagesProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface StagesProductionRepository extends JpaRepository <StagesProduction, Long>, JpaSpecificationExecutor<StagesProduction> {

    /**
     * Find a Stages of production by ID which contains in input set of IDs
     * @param ids - income set of IDs to search for
     * @return set of StagesProduction
     */
    Set<StagesProduction> getStagesProductionsByIdIn(Set<Long> ids);

    /**
     * The same method as upper one, but done manual with a StreamAPI
     * @param ids - income set of IDs to search for
     * @return set of StagesProduction
     */
    default Set<StagesProduction> getStagesProductionByIds(Set<Long> ids) {
        return findAll().stream()
                .filter(stagesProduction -> ids.contains(stagesProduction.getId()))
                .collect(Collectors.toSet());
    }

    @Query("from StagesProduction s " +
            "where lower(concat(s.name, ' ', s.description)) " +
            "like lower(concat('%', :req, '%'))")
    List<StagesProduction> search(@Param("req") String request);

}

// Этапы