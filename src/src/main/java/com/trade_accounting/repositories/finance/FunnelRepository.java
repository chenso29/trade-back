package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.entity.finance.Funnel;
import com.trade_accounting.models.entity.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunnelRepository extends JpaRepository<Funnel, Long>, JpaSpecificationExecutor<Funnel> {
}
