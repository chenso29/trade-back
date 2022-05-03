package src.main.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.util.TaskCommentRepository;
import com.trade_accounting.repositories.util.TaskRepository;
import com.trade_accounting.services.interfaces.util.TaskCommentService;
import com.trade_accounting.utils.mapper.util.TaskCommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskCommentServiceImpl implements TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskCommentMapper taskCommentMapper;

    @Override
    public List<TaskCommentDto> search(Specification<TaskComment> specification) {
        return executeSearch(taskCommentRepository, taskCommentMapper::toDto, specification);
    }

    @Override
    public List<TaskCommentDto> getAll() {
        return taskCommentRepository.findAll()
                .stream()
                .map(taskCommentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskCommentDto getById(Long id) {
        return taskCommentMapper.toDto(taskCommentRepository.getOne(id));
    }

    @Override
    public TaskCommentDto create(TaskCommentDto dto) {
        TaskComment taskComment = taskCommentMapper.toModel(dto);
        return taskCommentMapper.toDto(taskCommentRepository.save(taskComment));

    }

    public void createAll(List<TaskCommentDto> dtos) {
        dtos.forEach(dto -> {
            var entity = taskCommentMapper.toModel(dto);
            entity.setTask(taskRepository.getOne(dto.getTaskId()));
            entity.setPublisher(employeeRepository.getOne(dto.getPublisherId()));

            var taskOption = taskRepository.findById(dto.getTaskId());

            if (taskOption.isPresent()) {
                taskCommentRepository.save(entity);
                taskOption.get().getTaskComments().add(entity);
            }
        });

    }

    @Override
    public TaskCommentDto update(TaskCommentDto dto) {
        return this.create(dto);
    }

    @Override
    public void deleteById(Long id) {
        taskCommentRepository.deleteById(id);
    }
}

