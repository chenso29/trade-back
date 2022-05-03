package src.main.java.com.trade_accounting.repositories.retail;

import com.trade_accounting.models.entity.retail.RetailReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailReturnRepository extends JpaRepository<RetailReturn, Long>, JpaSpecificationExecutor<RetailReturn> {

    @Query("from RetailReturn t" +
            " where lower(concat(t.id, ' ', t.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<RetailReturn> search(@Param("req") String request);

}
