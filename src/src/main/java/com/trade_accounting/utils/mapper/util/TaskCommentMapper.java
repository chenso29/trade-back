package src.main.java.com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.util.Task;
import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {

    default TaskComment toModel(TaskCommentDto taskCommentDto) {
        if (taskCommentDto == null) {
            return null;
        }

        return TaskComment.builder()
                .id(taskCommentDto.getId())
                .commentContent(taskCommentDto.getCommentContent())
                .publishedDateTime(LocalDateTime.parse(taskCommentDto.getPublishedDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .publisher(publisher(taskCommentDto))
                .task(task(taskCommentDto))
                .build();
    }

    default TaskCommentDto toDto(TaskComment taskComment) {
        TaskCommentDto taskCommentDto = new TaskCommentDto();
        if (taskComment == null) {
            return null;
        } else {
            taskCommentDto.setId(taskComment.getId());
            taskCommentDto.setCommentContent(taskComment.getCommentContent());
            taskCommentDto.setPublishedDateTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(taskComment.getPublishedDateTime()));

            if (taskComment.getPublisher() == null) {
                return null;
            } else {
                taskCommentDto.setPublisherId(taskComment.getPublisher().getId());

                if (taskComment.getTask() == null) {
                    return null;
                } else {
                    taskCommentDto.setTaskId(taskComment.getTask().getId());
                    return taskCommentDto;
                }
            }
        }
    }

    default Employee publisher(TaskCommentDto taskCommentDto){
        Employee employee = new Employee();
        employee.setId(taskCommentDto.getPublisherId());
        return employee;
    }

    default Task task(TaskCommentDto taskCommentDto){
        Task task = new Task();
        task.setId(taskCommentDto.getTaskId());
        return task;
    }
}
