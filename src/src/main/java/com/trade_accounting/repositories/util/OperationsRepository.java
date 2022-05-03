package src.main.java.com.trade_accounting.repositories.util;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationsRepository extends JpaRepository<OperationsAbstract,Long>,
        JpaSpecificationExecutor<OperationsAbstract> {

    @Query("from OperationsAbstract o " +
            "where lower ( concat(o.id, ' ', o.company.name)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<OperationsAbstract> getBySearch(@Param("symbols") String search);

    @Query("from OperationsAbstract o " +
            "where lower ( concat(o.id, ' ', o.company.name)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<OperationsAbstract> getBySearchDeleted(@Param("symbols") String search);
}
