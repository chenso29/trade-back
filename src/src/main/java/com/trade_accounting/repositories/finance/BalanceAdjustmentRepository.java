package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.BalanceAdjustment;
import com.trade_accounting.models.dto.finance.BalanceAdjustmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BalanceAdjustmentRepository extends JpaRepository<BalanceAdjustment, Long>, JpaSpecificationExecutor<BalanceAdjustment> {

    @Query("select new com.trade_accounting.models.dto.finance.BalanceAdjustmentDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.contractor.id," +
            "e.account," +
            "e.cashOffice," +
            "e.comment," +
            "e.dateChanged, " +
            "e.whoChanged) from BalanceAdjustment  e")
    List<BalanceAdjustmentDto> getAll();

    @Query("select new com.trade_accounting.models.dto.finance.BalanceAdjustmentDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.contractor.id," +
            "e.account," +
            "e.cashOffice," +
            "e.comment," +
            "e.dateChanged, " +
            "e.whoChanged) from BalanceAdjustment  e where  e.id = :id")
    BalanceAdjustmentDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.finance.BalanceAdjustmentDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.contractor.id," +
            "e.account," +
            "e.cashOffice," +
            "e.comment," +
            "e.dateChanged, " +
            "e.whoChanged) from BalanceAdjustment  e where  lower(e.comment) " +
            "                                   like lower(concat('%', :nameFilter,'%'))"
    )
    List<BalanceAdjustmentDto> searchByNameFilter(@Param("nameFilter") String nameFilter);

    @Query("select new com.trade_accounting.models.dto.finance.BalanceAdjustmentDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.contractor.id," +
            "e.account," +
            "e.cashOffice," +
            "e.comment," +
            "e.dateChanged, " +
            "e.whoChanged) from BalanceAdjustment  e where  e.id = :nameFilter "
    )
    List<BalanceAdjustmentDto> searchById(@Param("nameFilter") Long nameFilter);
}
