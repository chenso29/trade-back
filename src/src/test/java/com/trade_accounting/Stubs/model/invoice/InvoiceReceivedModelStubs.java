package src.test.java.com.trade_accounting.Stubs.model.invoice;

import com.trade_accounting.Stubs.model.warehouse.AcceptanceModelStubs;
import com.trade_accounting.Stubs.model.company.CompanyModelStubs;
import com.trade_accounting.Stubs.model.company.ContractorModelStubs;
import com.trade_accounting.models.entity.invoice.InvoiceReceived;


public class InvoiceReceivedModelStubs {
    public static InvoiceReceived getInvoiceReceived(Long id) {
        return InvoiceReceived.builder()
                .id(id)
                .acceptance(AcceptanceModelStubs.getAcceptance(id))
                .contractor(ContractorModelStubs.getContractor(id))
                .company(CompanyModelStubs.getCompany(id))
                .build();
    }
}
