package src.test.java.com.trade_accounting.Stubs.model.retail;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.model.util.BonusProgramModelStubs;
import com.trade_accounting.Stubs.model.util.FileModelStubs;
import com.trade_accounting.Stubs.model.util.TaskModelStubs;
import com.trade_accounting.models.entity.retail.RetailOperationWithPoints;

import java.time.LocalDateTime;

import static com.trade_accounting.Stubs.model.util.FileModelStubs.getFiles;

public class RetailOperationWithPointsModelStubs {

    public static RetailOperationWithPoints getRetailOperationWithPoints(Long id) {
        return RetailOperationWithPoints.builder()
                .id(id)
                .number(100L)
                .currentTime(LocalDateTime.now())
                .typeOperation("Начисление")
                .numberOfPoints(10000L)
                .accrualDate(LocalDateTime.now())
                .bonusProgram(BonusProgramModelStubs.getBonusProgram(1L))
                .contractor(ModelStubs.getContractor(1L))
                .task(TaskModelStubs.getTask(1L))
                .files(FileModelStubs.getFiles())
                .build();
    }
}
