package src.main.java.com.trade_accounting.repositories.invoice;

import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long>, JpaSpecificationExecutor<InvoiceProduct> {

    @Query("select new com.trade_accounting.models.dto.invoice.InvoiceProductDto(" +
            "e.id," +
            "e.invoice.id," +
            "e.product.id," +
            "e.amount," +
            "e.price) from InvoiceProduct e")
    List<InvoiceProductDto> getAll();

    @Query("select new com.trade_accounting.models.dto.invoice.InvoiceProductDto(" +
            "e.id," +
            "e.invoice.id," +
            "e.product.id," +
            "e.amount," +
            "e.price) from InvoiceProduct e where e.id =:id")
    InvoiceProductDto getById(@Param("id") Long id);

    @Query("select ip from InvoiceProduct ip where ip.invoice.id =:id")
    List<InvoiceProduct> getByInvoiceId(@Param("id") Long id);

    List<InvoiceProduct> getAllByProductId(Long id);

}
