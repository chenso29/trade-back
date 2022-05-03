package src.main.java.com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyersReturnRepository extends JpaRepository<BuyersReturn, Long>, JpaSpecificationExecutor<BuyersReturn> {
    @Query("select new com.trade_accounting.models.dto.warehouse.BuyersReturnDto(" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.contractor.id," +
            "e.company.id," +
            "e.sum," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment) from BuyersReturn e where e.contractor.id = :id")
    List<BuyersReturnDto> findByContractorId(Long id);

    @Query("select new com.trade_accounting.models.dto.warehouse.BuyersReturnDto(" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.contractor.id," +
            "e.company.id," +
            "e.sum," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment) from BuyersReturn e where lower(concat(e.id, e.comment, e.warehouse.name, e.company.name, e.contractor.name)) " +
            "like concat('%', :search, '%')")
    List<BuyersReturnDto> findBySearch(@Param("search") String search);


}
