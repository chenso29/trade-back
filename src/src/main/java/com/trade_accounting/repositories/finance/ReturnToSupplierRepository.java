package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.ReturnToSupplier;
import com.trade_accounting.models.dto.finance.ReturnToSupplierDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnToSupplierRepository extends JpaRepository<ReturnToSupplier, Long>, JpaSpecificationExecutor<ReturnToSupplier> {

    @Query("select new com.trade_accounting.models.dto.finance.ReturnToSupplierDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnToSupplier  e")
    List<ReturnToSupplierDto> getAll();

    @Query("select new com.trade_accounting.models.dto.finance.ReturnToSupplierDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnToSupplier  e where  e.id = :id")
    ReturnToSupplierDto getById(@Param("id") Long id);

    @Query(
            "select new com.trade_accounting.models.dto.finance.ReturnToSupplierDto (" +
                    "e.id," +
                    "e.date," +
                    "e.warehouse.id," +
                    "e.company.id," +
                    "e.contractor.id," +
                    "e.contract.id," +
                    "e.isSend," +
                    "e.isPrint, " +
                    "e.comment) from ReturnToSupplier  e where  lower(e.comment) " +
                    "                                   like lower(concat('%', :nameFilter,'%'))"
    )
    List<ReturnToSupplierDto> searchByNameFilter(@Param("nameFilter") String nameFilter);

    @Query("select new com.trade_accounting.models.dto.finance.ReturnToSupplierDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnToSupplier  e where  e.id = :nameFilter "
    )
    List<ReturnToSupplierDto> searchById(@Param("nameFilter") Long nameFilter);


}
