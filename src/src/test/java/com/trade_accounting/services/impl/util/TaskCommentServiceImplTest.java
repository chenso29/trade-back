package src.test.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.util.TaskCommentRepository;
import com.trade_accounting.repositories.util.TaskRepository;
import com.trade_accounting.Stubs.dto.util.TaskCommentDtoStubs;
import com.trade_accounting.Stubs.model.util.TaskCommentModalStubs;
import com.trade_accounting.utils.mapper.util.TaskCommentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskCommentServiceImplTest {

    @InjectMocks
    private TaskCommentServiceImpl taskCommentService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TaskCommentRepository taskCommentRepository;

    @Mock
    private TaskRepository taskRepository;

    @Spy
    private TaskCommentMapper dtoMapper;

    @Test
    void getAll() {
        when(taskCommentRepository.findAll())
                .thenReturn(
                        List.of(
                                TaskCommentModalStubs.getTaskComment(1L),
                                TaskCommentModalStubs.getTaskComment(2L),
                                TaskCommentModalStubs.getTaskComment(3L)
                        ));
        List<TaskCommentDto> taskCommentDtos = taskCommentService.getAll();

        assertEquals(3, taskCommentDtos.size());
    }

    @Test
    void getById() {
        when(taskCommentRepository.getOne(anyLong()))
                .thenReturn(TaskCommentModalStubs.getTaskComment(1L));

        TaskCommentDto taskCommentDto = taskCommentService.getById(1L);

        assertEquals(1, taskCommentDto.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        taskCommentService.deleteById(anyLong());
        verify(taskCommentRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(taskCommentRepository.save(any(TaskComment.class)))
                .thenReturn(TaskCommentModalStubs.getTaskComment(1L));

        TaskCommentDto taskCommentDto = taskCommentService.create(TaskCommentDtoStubs.getDto(1L));

        assertEquals(1, taskCommentDto.getId());
        verify(taskCommentRepository).save(any(TaskComment.class));
    }






}
