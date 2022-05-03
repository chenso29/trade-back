package src.test.java.com.trade_accounting.Stubs.model.company;

import com.trade_accounting.models.entity.company.Contractor;

public class ContractorModelStubs {
    public static Contractor getContractor(Long id) {
        return Contractor.builder()
                .id(id)
                .build();
    }
}
