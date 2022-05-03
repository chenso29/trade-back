package src.main.java.com.trade_accounting.repositories.invoice;

import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface InvoicesStatusRepository extends JpaRepository<InvoicesStatus, Long>, JpaSpecificationExecutor<Image> {

    @Query("select new com.trade_accounting.models.dto.invoice.InvoicesStatusDto(" +
            "i.id, " +
            "i.statusName) from InvoicesStatus i")
    List<InvoicesStatusDto> getAll();

    @Query("select new com.trade_accounting.models.dto.invoice.InvoicesStatusDto(" +
            "i.id, " +
            "i.statusName) from InvoicesStatus i  where i.id = :id")
    InvoicesStatusDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.invoice.InvoicesStatusDto(" +
            "i.id, " +
            "i.statusName) from InvoicesStatus i where i.statusName = :statusName")
    InvoicesStatusDto getByName(@Param("statusName") String statusName);


    @Query("select i.typeOfInvoice from Invoice i where i.id = :id")
    InvoicesStatus getInvoicesStatusByInvoiceId(@Param("id") Long id);

    Optional<InvoicesStatus> findByStatusName (String statusName);

    InvoicesStatus getInvoicesStatusById(Long id);
}