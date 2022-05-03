package src.main.java.com.trade_accounting.services.interfaces.util;

import com.trade_accounting.models.entity.util.Task;
import com.trade_accounting.models.dto.util.TaskDto;

import java.util.List;

public interface TaskService extends AbstractService<TaskDto>, SearchableService<Task, TaskDto> {

    void createAll(List<TaskDto> dtos);

}
