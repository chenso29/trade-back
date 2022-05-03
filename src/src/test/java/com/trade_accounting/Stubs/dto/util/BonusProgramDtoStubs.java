package src.test.java.com.trade_accounting.Stubs.dto.util;

import com.trade_accounting.Stubs.model.util.BonusProgramModelStubs;
import com.trade_accounting.models.dto.util.BonusProgramDto;
import com.trade_accounting.utils.mapper.util.BonusProgramMapper;
import org.mapstruct.factory.Mappers;

public class BonusProgramDtoStubs {

    private static final BonusProgramMapper mapper = Mappers.getMapper(BonusProgramMapper.class);

    public static BonusProgramDto getDto(Long id) {
        return mapper.toDto(BonusProgramModelStubs.getBonusProgram(id));
    }
}
