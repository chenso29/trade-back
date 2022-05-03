package src.main.java.com.trade_accounting.models.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private Long id;

    private byte[] content;

    private String fileExtension;

    private String sortNumber;

    public ImageDto(Long id, String sortNumber) {
        this.id = id;
        this.sortNumber = sortNumber;
    }

    @Override
    public String toString() {
        return "ImageDto(" +
                "id=" + id +
                ", fileExtension='" + fileExtension + '\'' +
                ", sortNumber='" + sortNumber + '\'' +
                ')';
    }
}
