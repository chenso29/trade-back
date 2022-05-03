package src.test.java.com.trade_accounting.Stubs.dto.util;

import com.trade_accounting.models.dto.util.TaskCommentDto;
import com.trade_accounting.Stubs.model.util.TaskCommentModalStubs;
import com.trade_accounting.utils.mapper.util.TaskCommentMapper;
import org.mapstruct.factory.Mappers;

public class TaskCommentDtoStubs {

    private static final TaskCommentMapper mapper = Mappers.getMapper(TaskCommentMapper.class);

    public static TaskCommentDto getDto(Long id) {
        return mapper.toDto(TaskCommentModalStubs.getTaskComment(id));
    }

}
