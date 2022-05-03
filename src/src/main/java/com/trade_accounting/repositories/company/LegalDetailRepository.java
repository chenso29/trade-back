package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.LegalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LegalDetailRepository extends JpaRepository<LegalDetail, Long> {

    Optional<LegalDetail> findByInn(String name);

    @Query("SELECT c FROM LegalDetail c")
    List<LegalDetail> getAll();

    @Query("SELECT c FROM LegalDetail c WHERE c.id = :id")
    LegalDetail getById(@Param("id") Long id);

    LegalDetail getLegalDetailById(Long id);
}
