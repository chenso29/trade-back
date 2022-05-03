package src.main.java.com.trade_accounting.repositories.retail;

import com.trade_accounting.models.entity.retail.RetailStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailStoreRepository extends JpaRepository<RetailStore, Long>, JpaSpecificationExecutor<RetailStore> {

    @Query("from RetailStore s" +
            " where lower(concat(s.id, ' '))" +
            " like lower(concat('%', :req, '%'))")
    List<RetailStore> search(@Param("req") String request);
}
