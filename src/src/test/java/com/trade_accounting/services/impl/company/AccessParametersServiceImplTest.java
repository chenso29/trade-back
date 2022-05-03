package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.AccessParameters;
import com.trade_accounting.models.dto.company.AccessParametersDto;
import com.trade_accounting.repositories.company.AccessParametersRepository;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.company.AccessParametersDtoStubs;
import com.trade_accounting.utils.mapper.company.AccessParametersMapper;
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
class AccessParametersServiceImplTest {
    @Mock
    private AccessParametersRepository accessParametersRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Spy
    private AccessParametersMapper accessParametersMapper;

    @InjectMocks
    private AccessParametersServiceImpl accessParametersService;

    @Test
    void getAll_shouldReturnListFilledAccessParametersDto() {
        when(accessParametersRepository.findAll())
                .thenReturn(
                        List.of(
                                ModelStubs.getAccessParameters(1L),
                                ModelStubs.getAccessParameters(2L),
                                ModelStubs.getAccessParameters(3L)
                        )
                );

        List<AccessParametersDto> accessParameters = accessParametersService.getAll();

        assertEquals(3, accessParameters.size());
    }

    @Test
    void getById_shouldReturnFilledAccessParameters() {
        when(accessParametersRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getAccessParameters(1L));

        AccessParametersDto accessParametersDto = accessParametersService.getById(1L);

        assertEquals(1, accessParametersDto.getId());
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
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        accessParametersService.deleteById(anyLong());
        verify(accessParametersRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(accessParametersRepository.save(any(AccessParameters.class)))
                .thenReturn(ModelStubs.getAccessParameters(1L));

        AccessParametersDto accessParametersDto =
                accessParametersService.create(AccessParametersDtoStubs.getAccessParametersDto(1L));

        assertEquals(1, accessParametersDto.getId());

        verify(accessParametersRepository).save(any(AccessParameters.class));
    }
}