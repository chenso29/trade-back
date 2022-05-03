package src.test.java.com.trade_accounting.Stubs.dto.client;

import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.client.EmployeeMapper;
import org.mapstruct.factory.Mappers;

public class EmployeeDtoStubs {
    private static final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
    public static EmployeeDto getEmployeeDto(Long id) {
        return mapper.toDto(
                ModelStubs.getEmployee(id)
        );
    }
}
