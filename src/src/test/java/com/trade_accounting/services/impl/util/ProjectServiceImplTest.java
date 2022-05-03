package src.test.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.models.entity.util.Project;
import com.trade_accounting.models.dto.util.ProjectDto;
import com.trade_accounting.repositories.util.ProjectRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.util.ProjectDtoStubs;
import com.trade_accounting.utils.mapper.util.ProjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Spy
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void getAll_shouldReturnListFilledProjectDto() {
        when(projectRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getProject(1L),
                                ModelStubs.getProject(2L),
                                ModelStubs.getProject(3L)
                        ).collect(Collectors.toList())
                );

        List<ProjectDto> projects = projectService.getAll();

        assertNotNull(projects, "failure - expected that a list of projectDto not null");
        assertTrue(projects.size() > 0, "failure - expected that a list of projectDto grater than 0");

        for (ProjectDto project : projects) {
            projectDtoIsCorrectlyInited(project);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListProjectDto() {
        when(projectRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<ProjectDto> projects = projectService.getAll();

        assertNotNull(projects, "failure - expected that a list of projectDto not null");
        assertEquals(0, projects.size(), "failure - expected that size of list of projectDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledProjectDto() {
        Optional<Project> projectFromRepo =
                Optional.of(ModelStubs.getProject(1L));

        when(projectRepository.findById(anyLong()))
                .thenReturn(projectFromRepo);

        ProjectDto project = projectService.getById(1L);

        assertNotNull(project, "failure - expected that project not null");
        projectDtoIsCorrectlyInited(project);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        projectService.create(
                ProjectDtoStubs.getProjectDto(1L)
        );

        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        projectService.update(
                ProjectDtoStubs.getProjectDto(1L)
        );

        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void deleteById() {
        projectService.deleteById(1L);
        verify(projectRepository).deleteById(1L);
    }

    void projectDtoIsCorrectlyInited(ProjectDto project) {
        assertNotNull(project, "Fail in passed employee");
        assertNotNull(project.getId(), "Fail in field 'id' of project");
        assertNotNull(project.getName(), "Fail in field 'name' of project");
    }
}