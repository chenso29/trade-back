package src.test.java.com.trade_accounting.Stubs.dto.client;

import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.client.DepartmentMapper;
import org.mapstruct.factory.Mappers;

public class DepartmentDtoStubs {
    private static final DepartmentMapper mapper = Mappers.getMapper(DepartmentMapper.class);
    public static DepartmentDto getDepartmentDto(Long id) {
        return mapper.toDto(
                ModelStubs.getDepartment(id));
    }
}
