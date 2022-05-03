package src.test.java.com.trade_accounting.Stubs.model.util;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.util.Task;

import java.time.LocalDateTime;
import java.util.List;

public class TaskModelStubs {

    public static Task getTask(Long id) {
        return Task.builder()
                .id(id)
                .description("Some description")
                .taskEmployee(ModelStubs.getEmployee(id))
                .taskAuthor(ModelStubs.getEmployee(id))
                .creationDateTime(LocalDateTime.now())
                .deadlineDateTime(LocalDateTime.now())
                .completed(true)
                .taskComments(List.of(ModelStubs.getTaskComment(id)))
                .build();
    }
}
