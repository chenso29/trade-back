package src.test.java.com.trade_accounting.Stubs.model.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.Stubs.ModelStubs;

import java.time.LocalDateTime;

/**
 * @author Andrey Melnikov and Pavel Andrusov
 * @since 05.08.2021
 */
public class PriceListModelStubs {
    public static PriceList getPriceList(Long id) {
        return PriceList.builder()
                .id(id)
                .number("NumberOne")
                .time(LocalDateTime.parse(LocalDateTime.now().toString()))
                .company(ModelStubs.getCompany(id))
                .sent(false)
                .printed(false)
                .commentary("comment" + id)
                .build();
    }
}
