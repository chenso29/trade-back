package src.main.java.com.trade_accounting.services.interfaces.util;

import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.util.TaskCommentDto;

import java.util.List;

public interface TaskCommentService extends AbstractService<TaskCommentDto>,
        SearchableService<TaskComment, TaskCommentDto> {

    void createAll(List<TaskCommentDto> dtos);

}
