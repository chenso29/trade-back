package src.test.java.com.trade_accounting.Stubs.model.util;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.util.TaskComment;

import java.time.LocalDateTime;

public class TaskCommentModalStubs {
    public static TaskComment getTaskComment(Long id) {
        return TaskComment.builder()
                .id(id)
                .commentContent("Comment " + id)
                .publisher(ModelStubs.getEmployee(1L))
                .publishedDateTime(LocalDateTime.now())
                .task(ModelStubs.getTask(1L))
                .build();
    }
}
