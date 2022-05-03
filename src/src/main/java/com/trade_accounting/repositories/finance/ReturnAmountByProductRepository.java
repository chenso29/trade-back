package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.ReturnAmountByProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnAmountByProductRepository extends JpaRepository<ReturnAmountByProduct, Long> {

    @Query("select ra from ReturnAmountByProduct ra where ra.productId = :productId and ra.invoiceId = :invoiceId")
    ReturnAmountByProduct findAmountByProductIdAndInvoiceId(
            @Param("productId") Long productId,
            @Param("invoiceId") Long invoiceId);

}
