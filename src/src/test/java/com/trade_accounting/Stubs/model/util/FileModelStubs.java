package src.test.java.com.trade_accounting.Stubs.model.util;

import com.trade_accounting.models.entity.util.File;

import java.time.LocalDateTime;
import java.util.List;

public class FileModelStubs {

    public static List<File> getFiles() {
        return List.of(File.builder()
                .id(1L)
                .name("file.docx")
                .key("d2sfefqf32f4gf23f13t42gq2evg23g2")
                .uploadDateTime(LocalDateTime.now())
                .build());
    }

    public static File getFiles(Long id) {
        return File.builder()
                .id(id)
                .name("file.docx")
                .key("d2sfefqf32f4gf23f13t42gq2evg23g2")
                .uploadDateTime(LocalDateTime.now())
                .build();
    }
}
