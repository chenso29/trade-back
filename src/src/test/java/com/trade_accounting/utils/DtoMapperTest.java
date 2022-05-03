package src.test.java.com.trade_accounting.utils;

import com.trade_accounting.models.entity.warehouse.ProductGroup;
import com.trade_accounting.models.entity.util.Task;
import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import com.trade_accounting.models.dto.util.TaskDto;
import com.trade_accounting.utils.mapper.util.TaskCommentMapperImpl;
import com.trade_accounting.utils.mapper.util.TaskMapperImpl;
import com.trade_accounting.utils.mapper.warehouse.ProductGroupMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DtoMapperTest {

    @Spy
    private ProductGroupMapperImpl productGroupMapper;

    @Spy
    private TaskMapperImpl taskMapper;

    @Spy
    private TaskCommentMapperImpl taskCommentMapper;

    @Test
    void productGroupToProductGroupDto() {
        ProductGroupDto productGroupDto = new ProductGroupDto();
        productGroupDto.setParentId(1L);

        ProductGroup productGroup = productGroupMapper.toModel(productGroupDto);
        assertEquals(productGroupDto.getParentId(), productGroup.getProductGroup().getId());
    }

    @Test
    void taskToTaskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setEmployeeId(1L);
        taskDto.setTaskAuthorId(1L);

        Task taskEmployee = taskMapper.taskDtoToTask(taskDto);
        assertEquals(taskDto.getEmployeeId(), taskEmployee.getTaskEmployee().getId());

        Task taskAuthor = taskMapper.taskDtoToTask(taskDto);
        assertEquals(taskDto.getTaskAuthorId(), taskAuthor.getTaskAuthor().getId());
    }

    @Test
    void taskCommentToTaskCommentDto() {
        TaskCommentDto taskCommentDto = new TaskCommentDto();
        taskCommentDto.setPublisherId(1L);
        taskCommentDto.setTaskId(1L);

        TaskComment taskCommentPublisher = taskCommentMapper.toModel(taskCommentDto);
        assertEquals(taskCommentDto.getPublisherId(), taskCommentPublisher.getPublisher().getId());

        TaskComment taskCommentTask = taskCommentMapper.toModel(taskCommentDto);
        assertEquals(taskCommentDto.getTaskId(), taskCommentTask.getTask().getId());

    }
}
