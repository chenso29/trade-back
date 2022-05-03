package src.main.java.com.trade_accounting.repositories.invoice;

import com.trade_accounting.models.entity.invoice.InvoiceReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceReceivedRepository extends JpaRepository<InvoiceReceived, Long>, JpaSpecificationExecutor<InvoiceReceived> {

    @Query("from InvoiceReceived e" +
            " where lower(concat(e.id, ' ', e.company.name))" +
            " like lower(concat('%', :req, '%'))")
    List<InvoiceReceived> searchString(@Param("req") String request);

}
