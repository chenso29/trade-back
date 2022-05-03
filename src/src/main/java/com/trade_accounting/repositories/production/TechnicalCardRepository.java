package src.main.java.com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicalCardRepository extends JpaRepository<TechnicalCard, Long>, JpaSpecificationExecutor<TechnicalCard> {

    @Query("from TechnicalCard t" +
            " where lower(concat(t.name, ' ', t.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<TechnicalCard> search(@Param("req") String request);

    @Query("from TechnicalCard t where t.technicalCardGroup.id = :id")
    List<TechnicalCard> getAllByTechnicalCardGroupId(@Param("id") Long id);

    TechnicalCard getTechnicalCardById(Long id);
}
