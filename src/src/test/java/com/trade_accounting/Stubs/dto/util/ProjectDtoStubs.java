package src.test.java.com.trade_accounting.Stubs.dto.util;

import com.trade_accounting.models.dto.util.ProjectDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.util.ProjectMapper;
import org.mapstruct.factory.Mappers;

public class ProjectDtoStubs {
    private static final ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);
    public static ProjectDto getProjectDto(Long id) {
        return mapper.toDto(
                ModelStubs.getProject(id)
        );
    }
}
