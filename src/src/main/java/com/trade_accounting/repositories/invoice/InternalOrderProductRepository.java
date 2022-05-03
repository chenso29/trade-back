package src.main.java.com.trade_accounting.repositories.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InternalOrderProductRepository extends JpaRepository<InternalOrderProduct, Long>,
        JpaSpecificationExecutor<InternalOrderProduct> {
}
