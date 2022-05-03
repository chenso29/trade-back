package src.test.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.models.dto.util.ImageDto;
import com.trade_accounting.repositories.util.ImageRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.util.ImageMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {
    @Mock
    private ImageRepository imageRepository;

    @Spy
    private ImageMapperImpl imageMapper;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    void whenGetAllThenReturnAllImageDTOList() {
        when(imageRepository.getAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getImage(1L),
                                ModelStubs.getImage(2L),
                                ModelStubs.getImage(3L)
                        ).collect(Collectors.toList())
                );

        List<ImageDto> imageDtos = imageService.getAll();

        assertNotNull(imageDtos, "failure - expected that list of mages not null");
        assertTrue(imageDtos.size() > 0, "failure - expected that size of list of position greater than 0");
    }

    @Test
    void whenGetByIdThenReturnImageDto() {
        Image image = ModelStubs.getImage(1L);
        when(imageRepository.getOne(anyLong())).thenReturn(image);

        ImageDto imageDto1 = imageMapper.toDto(imageRepository.getOne(1L));
        ImageDto imageDto2 = imageService.getById(1L);
        assertEquals(imageDto1, imageDto2);
    }

    @Test
    void whenDeleteByIdThenDelete() {
        imageService.deleteById(1L);
        verify(imageRepository).findById(1L);
    }

    @Test
    void whenCreateImage() {
        ImageDto imageDto = ImageDto.builder()
                .id(1L)
                .content("Test".getBytes())
                .fileExtension(".jpg")
                .sortNumber("0001")
                .build();
        imageService.create(imageDto);
        verify(imageRepository).save(any(Image.class));
    }
}