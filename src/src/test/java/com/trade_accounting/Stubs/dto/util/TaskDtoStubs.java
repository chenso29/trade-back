package src.test.java.com.trade_accounting.Stubs.dto.util;

import com.trade_accounting.models.dto.util.TaskDto;
import com.trade_accounting.Stubs.model.util.TaskModelStubs;
import com.trade_accounting.utils.mapper.util.TaskMapper;
import org.mapstruct.factory.Mappers;

public class TaskDtoStubs {

    private static final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    public static TaskDto toDto(Long id) {
        return taskMapper.taskToTaskDto(TaskModelStubs.getTask(id));

    }

}
