package src.main.java.com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.SerialNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SerialNumbersRepository extends JpaRepository<SerialNumbers, Long>,
        JpaSpecificationExecutor<SerialNumbers> {

    @Query("from SerialNumbers s" +
            " where lower(concat(s.id, ' '))" +
            " like lower(concat('%', :req, '%'))")
    List<SerialNumbers> searchString(@Param("req") String request);
}
