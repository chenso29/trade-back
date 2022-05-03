package src.main.java.com.trade_accounting.models.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String description;

    private Long employeeId;

    private Long contractorId;

    @NotNull
    private Long taskAuthorId;

    @NotNull
    private String creationDateTime;

    @NotNull
    private String deadlineDateTime;

    private boolean completed;

    private List<Long> taskCommentsIds;

    public TaskDto(Long id,
                   String description,
                   Long contractorId,
                   Long employeeId,
                   Long taskAuthorId,
                   LocalDateTime creationDateTime,
                   LocalDateTime deadlineDateTime,
                   boolean completed,
                   List<Long> taskCommentsIds) {
        this.id = id;
        this.description = description;
        this.contractorId = contractorId;
        this.employeeId = employeeId;
        this.taskAuthorId = taskAuthorId;
        this.creationDateTime = creationDateTime.toString();
        this.deadlineDateTime = deadlineDateTime.toString();
        this.completed = completed;
        this.taskCommentsIds = taskCommentsIds;
    }
}
