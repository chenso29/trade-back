package src.main.java.com.trade_accounting.repositories.util;

import com.trade_accounting.models.entity.util.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Long>, JpaSpecificationExecutor<TaskComment> {

    int countTaskCommentByTaskId(long taskId);

    @Query("select comment.id from TaskComment comment where comment.task.id = ?1")
    Collection<Long> getCommentsIdByTaskId(long taskId);
}
