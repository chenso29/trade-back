package src.test.java.com.trade_accounting.Stubs.model.production;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.production.RequestsProductions;

import java.time.LocalDate;

import static com.trade_accounting.Stubs.ModelStubs.getWarehouse;

public class RequestsProductionsModelStubs {
    public static RequestsProductions getRequestsProductions(Long id) {
        return RequestsProductions.builder()
                .id(id)
                .numberOfTheCertificate("RP-001" + id)
                .dateOfTheCertificate(LocalDate.now())
                .technicalCard(ModelStubs.getTechnicalCard(id))
                .volume(500)
                .warehouse(ModelStubs.getWarehouse(id))
                .build();
    }
}

