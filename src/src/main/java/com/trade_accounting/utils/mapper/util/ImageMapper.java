package src.main.java.com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.models.dto.util.ImageDto;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    //Image
    default ImageDto toDto(Image image) {
        if (image == null) {
            return null;
        }
        return ImageDto.builder()
                .id(image.getId())
                .content(downloadImage(image.getImageUrl()))
                .sortNumber(image.getSortNumber())
                .build();
    }

    default Image toModel(ImageDto imageDto, String imageDir) {
        String url = uploadImage(imageDto.getContent(), imageDir,
                imageDto.getId() + imageDto.getFileExtension());
        return Image.builder()
                .id(imageDto.getId())
                .imageUrl(url)
                .build();
    }

    default List<Image> toListModel(Collection<ImageDto> imageDtos, String imageDir) {
        if (imageDtos == null) {
            return new ArrayList<>();
        }
        List<Image> list = new ArrayList<>(imageDtos.size());
        for (ImageDto imageDto : imageDtos) {
            list.add(toModel(imageDto, imageDir));
        }
        return list;
    }

    List<ImageDto> toListDto(Collection<Image> images);

    @SneakyThrows
    default String uploadImage(byte[] content, String imageDir, String fileName) {
        final String UPLOAD_DIR = "images";

        Path path = Paths.get(UPLOAD_DIR + File.separator + imageDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        path = Paths.get(path.toString() + File.separator + fileName);
        if (content != null) {
            Files.write(path, content);
        }
        return path.toString();
    }

    @SneakyThrows
    default byte[] downloadImage(String url) {
        Path path = Paths.get(url);
        if (Files.exists(path)) {
            return Files.readAllBytes(path);
        } else {
            return new byte[0];
        }
    }
}
