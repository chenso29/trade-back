package src.main.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.dto.util.FileDto;
import com.trade_accounting.repositories.util.FileRepository;
import com.trade_accounting.services.interfaces.util.FileService;
import com.trade_accounting.utils.mapper.util.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    private final FileMapper fileMapper;

    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }



    @Override
    public File getById(Long fileId) {
        return fileRepository.findById(fileId).get();
    }

    @Override
    public File create(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File update(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public FileDto create(FileDto fileDto) {
        File file = fileMapper.toModel(fileDto);
        return fileMapper.toDto(fileRepository.save(file));
    }

    @Override
    @SneakyThrows
    public void delete(Long fileId) {
        File file = fileRepository.getOne(fileId);
        fileRepository.deleteById(fileId);

        String fs = java.io.File.separator;
        Path path = Paths.get("src" + fs +"main" + fs +"resources" + fs +"file" + fs + file.getKey() + file.getExtension());
        Files.delete(path);
    }

    @Override
    public List<FileDto> search(Specification<File> spec) {
        return executeSearch(fileRepository, fileMapper::toDto, spec);
    }
}
