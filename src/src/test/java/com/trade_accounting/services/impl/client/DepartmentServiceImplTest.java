package src.test.java.com.trade_accounting.services.impl.client;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.client.DepartmentDtoStubs;
import com.trade_accounting.utils.mapper.client.DepartmentMapperImpl;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Spy
    private DepartmentMapperImpl departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void getAll_shouldReturnListFilledDepartmentDto() {
        when(departmentRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getDepartment(1L),
                                ModelStubs.getDepartment(2L),
                                ModelStubs.getDepartment(3L)
                        )
                                .collect(Collectors.toList())
                );

        List<DepartmentDto> departments = departmentService.getAll();

        assertNotNull(departments, "Failure - expected that list of department not null");
        assertTrue(departments.size() > 0, "failure - expected that size of list of department greater than 0");

        for (DepartmentDto departmentDto : departments) {
            departmentDtoIsCorrectlyInited(departmentDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListDepartmentDto() {
        when(departmentRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<DepartmentDto> departments = departmentService.getAll();

        assertNotNull(departments, "Failure - expected that list of department not null");
        assertEquals(0, departments.size(), "failure - expected that size of list of department equals 0");
    }

    @Test
    void getById_shouldReturnFilledDepartmentDto() {
        Optional<Department> departmentRepo = Optional.of(ModelStubs.getDepartment(1L));

        when(departmentRepository.findById(anyLong()))
                .thenReturn(departmentRepo);

        DepartmentDto departmentDto = departmentService.getById(1L);

        departmentDtoIsCorrectlyInited(departmentDto);
    }

    @Test
    void getByName_shouldReturnFilledDepartmentDto() {
        Optional<Department> departmentRepo = Optional.of(ModelStubs.getDepartment(1L));

        when(departmentRepository.findByName(anyString()))
                .thenReturn(departmentRepo);

        DepartmentDto departmentDto = departmentService.getByName("name");

        departmentDtoIsCorrectlyInited(departmentDto);
    }

    @Test
    void getByName_shouldReturnEmptyDepartmentDto() {
        Optional<Department> departmentRepo = Optional.empty();

        when(departmentRepository.findByName(anyString()))
                .thenReturn(departmentRepo);

        DepartmentDto departmentDto = departmentService.getByName("name");

        assertEquals(new DepartmentDto(), departmentDto, "failure - expected that department was empty");
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        departmentService.create(DepartmentDtoStubs.getDepartmentDto(1L));

        verify(departmentRepository).save(any(Department.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        departmentService.update(DepartmentDtoStubs.getDepartmentDto(1L));

        verify(departmentRepository).save(any(Department.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        departmentService.deleteById(1L);

        verify(departmentRepository).deleteById(1L);
    }

    void departmentDtoIsCorrectlyInited(DepartmentDto departmentDto) {
        assertNotNull(departmentDto, "failure - fail in passed departmentDto");
        assertNotNull(departmentDto.getId(), "failure - fail in field 'id' into departmentDto");
        assertNotNull(departmentDto.getName(), "failure - fail in field 'name' into departmentDto");
        assertNotNull(departmentDto.getSortNumber(), "failure - fail in field 'sortNumber' into departmentDto");
    }
}