package src.main.java.com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.Inventarization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarizationRepository extends JpaRepository<Inventarization, Long>,
        JpaSpecificationExecutor<Inventarization> {

    @Query("from Inventarization e" +
            " where lower(concat(e.id, ' ', e.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<Inventarization> search(@Param("req") String request);

}
