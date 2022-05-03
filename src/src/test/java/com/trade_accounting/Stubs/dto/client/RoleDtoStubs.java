package src.test.java.com.trade_accounting.Stubs.dto.client;

import com.trade_accounting.models.dto.client.RoleDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.client.RoleMapper;
import org.mapstruct.factory.Mappers;

public class RoleDtoStubs {
    private static final RoleMapper mapper = Mappers.getMapper(RoleMapper.class);
    public static RoleDto getRoleDto(Long id) {
        return mapper.toDto(
                ModelStubs.getRole(id)
        );
    }
}
