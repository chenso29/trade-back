package src.main.java.com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.util.Project;
import com.trade_accounting.models.dto.util.ProjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    //Project
    ProjectDto toDto(Project project);

    Project toModel(ProjectDto projectDto);
}
