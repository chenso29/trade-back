package src.test.java.com.trade_accounting.Stubs.dto.util;

import com.trade_accounting.models.dto.util.FileDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.util.FileMapper;
import org.mapstruct.factory.Mappers;

public class FileDtoStubs {
    private static final FileMapper mapper = Mappers.getMapper(FileMapper.class);

    public static FileDto getFileDto(Long id) {
        return mapper.toDto(ModelStubs.getFile(id));
    }
}
