package src.test.java.com.trade_accounting.Stubs.model.company;

import com.trade_accounting.models.entity.company.ContractorStatus;

public class ContractorStatusModelStubs {
    public static ContractorStatus getContractorStatus(Long id){
        return ContractorStatus.builder()
                .id(id)
                .name("Name " + id)
                .build();
    }
}
