package src.main.java.com.trade_accounting.models.entity.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "images")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sort_number")
    private String sortNumber;

    public Image(String imageUrl, String sortNumber) {
        this.imageUrl = imageUrl;
        this.sortNumber = sortNumber;
    }

    public Image(Long id, String sortNumber) {
        this.id = id;
        this.sortNumber = sortNumber;
    }

    @PreRemove
    private void deleteFile() {
        log.info("Удален экземпляр ImageDto с id={}", id);
        Path path = Paths.get(imageUrl);
        if (imageUrl.equals("src/main/resources/file/male.ico")) {
            return;
        }
        try {
            Files.deleteIfExists(path);
            log.info("Удален файл по url={}", imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
