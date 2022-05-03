package src.main.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.models.entity.util.Project;
import com.trade_accounting.models.dto.util.ProjectDto;
import com.trade_accounting.repositories.util.ProjectRepository;
import com.trade_accounting.services.interfaces.util.ProjectService;
import com.trade_accounting.utils.mapper.util.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getById(Long id) {
        return projectMapper.toDto(
                projectRepository.findById(id).orElse(new Project())
        );
    }

    @Override
    public ProjectDto create(ProjectDto projectDto) {
        Project project = projectRepository.save(
                projectMapper.toModel(projectDto)
        );
        projectDto.setId(project.getId());
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) {
        return create(projectDto);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
