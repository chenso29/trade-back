package src.main.java.com.trade_accounting.repositories.util;


import com.trade_accounting.models.entity.util.Project;
import com.trade_accounting.models.dto.util.ProjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select new com.trade_accounting.models.dto.util.ProjectDto(" +
            "e.id," +
            "e.name," +
            "e.code," +
            "e.description" +
            ") from Project e")
    List<ProjectDto> getAll();

    @Query("select new com.trade_accounting.models.dto.util.ProjectDto(" +
            "e.id," +
            "e.name," +
            "e.code," +
            "e.description" +
            ") from Project e")
    ProjectDto getById(@Param("id") Long id);
}
