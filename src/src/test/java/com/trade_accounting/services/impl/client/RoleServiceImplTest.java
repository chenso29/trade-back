package src.test.java.com.trade_accounting.services.impl.client;

import com.trade_accounting.models.entity.client.Role;
import com.trade_accounting.models.dto.client.RoleDto;
import com.trade_accounting.repositories.client.RoleRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.client.RoleDtoStubs;
import com.trade_accounting.utils.mapper.client.RoleMapper;
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
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @Spy
    private RoleMapper roleMapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void getAll_shouldReturnListFilledRoleDto() {
        when(roleRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getRole(1L),
                                ModelStubs.getRole(2L),
                                ModelStubs.getRole(3L)
                        ).collect(Collectors.toList())
                );

        List<RoleDto> roles = roleService.getAll();

        assertNotNull(roles, "failure - expected that a list of roleDto not null");
        assertTrue(roles.size() > 0, "failure - expected that a list of roleDto grater than 0");

        for (RoleDto role : roles) {
            roleDtoIsCorrectlyInited(role);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListRoleDto() {
        when(roleRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<RoleDto> roles = roleService.getAll();

        assertNotNull(roles, "failure - expected that a list of roleDto not null");
        assertEquals(0, roles.size(), "failure - expected that size of list of roleDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledRoleDto() {
        Optional<Role> roleFromRepo = Optional.of(ModelStubs.getRole(1L));

        when(roleRepository.findById(anyLong()))
                .thenReturn(roleFromRepo);

        RoleDto role = roleService.getById(1L);

        assertNotNull(role, "failure - expected that role not null");
        roleDtoIsCorrectlyInited(role);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        roleService.create(
                RoleDtoStubs.getRoleDto(1L)
        );

        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        roleService.update(
                RoleDtoStubs.getRoleDto(1L)
        );

        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void deleteById() {
        roleService.deleteById(1L);
        verify(roleRepository).deleteById(1L);
    }

    void roleDtoIsCorrectlyInited(RoleDto role) {
        assertNotNull(role, "Fail in passed employee");
        assertNotNull(role.getId(), "Fail in field 'id' of role");
        assertNotNull(role.getName(), "Fail in field 'name' of role");
    }
}