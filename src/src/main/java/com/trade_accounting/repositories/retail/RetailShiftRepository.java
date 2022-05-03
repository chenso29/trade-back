package src.main.java.com.trade_accounting.repositories.retail;

import com.trade_accounting.models.entity.retail.RetailShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailShiftRepository extends JpaRepository<RetailShift, Long>, JpaSpecificationExecutor<RetailShift> {

    @Query("from RetailShift e" +
            " where lower(concat(e.id, ' ', e.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<RetailShift> search(@Param("req") String request);
}
