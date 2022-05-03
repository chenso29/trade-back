package src.test.java.com.trade_accounting.Stubs.model.production;

import com.trade_accounting.models.entity.production.TechnicalCardGroup;

public class TechnicalCardGroupModelStubs {
    public static TechnicalCardGroup getTechnicalCardGroup(Long id){
        return TechnicalCardGroup.builder()
                .id(id)
                .name("Группа технических карт " + id)
                .comment("Комментарий " + id)
                .sortNumber(id.toString())
                .build();
    }
}
