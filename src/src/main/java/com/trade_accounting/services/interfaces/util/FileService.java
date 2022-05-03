package src.main.java.com.trade_accounting.services.interfaces.util;

import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.dto.util.FileDto;

import java.util.List;

public interface FileService extends AbstractService<File>, SearchableService<File, FileDto> {

    List<File> getAll();

    FileDto create(FileDto fileDto);

    File getById(Long fileId);

    void delete(Long fileId);
}
