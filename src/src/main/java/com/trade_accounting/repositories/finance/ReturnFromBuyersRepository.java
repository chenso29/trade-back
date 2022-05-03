package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.ReturnFromBuyers;
import com.trade_accounting.models.dto.finance.ReturnFromBuyersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnFromBuyersRepository extends JpaRepository<ReturnFromBuyers, Long>, JpaSpecificationExecutor<ReturnFromBuyers> {

    @Query("select new com.trade_accounting.models.dto.finance.ReturnFromBuyersDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.contractor.id," +
            "e.company.id," +
            "e.totalPrice," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint," +
            "e.comment," +
            "e.isConducted) from ReturnFromBuyers e")
    List<ReturnFromBuyersDto> getAll();


    @Query("select new com.trade_accounting.models.dto.finance.ReturnFromBuyersDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.contractor.id," +
            "e.company.id," +
            "e.totalPrice," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint," +
            "e.comment," +
            "e.isConducted) from ReturnFromBuyers e where e.id = :id")
    ReturnFromBuyersDto getById(@Param("id") Long id);

}
