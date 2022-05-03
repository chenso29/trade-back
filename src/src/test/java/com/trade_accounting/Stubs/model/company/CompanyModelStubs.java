package src.test.java.com.trade_accounting.Stubs.model.company;

import com.trade_accounting.models.entity.company.Company;

public class CompanyModelStubs {
    public static Company getCompany(Long id) {
        return Company.builder()
                .id(id)
                .build();
    }
}
