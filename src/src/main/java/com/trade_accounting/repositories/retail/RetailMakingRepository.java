package src.main.java.com.trade_accounting.repositories.retail;

import com.trade_accounting.models.entity.retail.RetailMaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RetailMakingRepository extends JpaRepository<RetailMaking, Long>, JpaSpecificationExecutor<RetailMaking> {

    @Query("from RetailMaking e" +
            " where lower(concat(e.id, ' ', e.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<RetailMaking> search(@Param("req") String request);

}
