package src.test.java.com.trade_accounting.services.impl.util;


import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.dto.util.FileDto;
import com.trade_accounting.repositories.util.FileRepository;
import com.trade_accounting.Stubs.dto.util.FileDtoStubs;
import com.trade_accounting.Stubs.model.util.FileModelStubs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceImplTest {

    @Mock
    FileRepository fileRepository;

    @InjectMocks
    FileServiceImpl fileService;

    @Test
    void getById() {
        when(fileRepository.findById(anyLong())).thenReturn(java.util.Optional.of(FileModelStubs.getFiles(1L)));

        File file = fileService.getById(1L);
        assertEquals(1, file.getId());
    }

    @Test
    void create() throws IOException {
        when(fileRepository.save(any(File.class))).thenReturn(FileModelStubs.getFiles(1L));
        byte[] arr = "MOckmockocmk".getBytes();
        FileDto fileDto = FileDtoStubs.getFileDto(1L);
        fileDto.setContent(arr);
        FileDto createdFile = fileService.create(fileDto);
        assertEquals(1,createdFile.getId());
        verify(fileRepository).save(any(File.class));
    }

    @Test
    void delete() {
        fileRepository.deleteById(anyLong());
        verify(fileRepository).deleteById(anyLong());
    }

}
