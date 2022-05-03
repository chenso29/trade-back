package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.dto.finance.ReturnAmountByProductDto;


public interface ReturnAmountByProductService {

    ReturnAmountByProductDto findAmountByProductIdAndInvoiceId(Long productId, Long invoiceId);
    
}
