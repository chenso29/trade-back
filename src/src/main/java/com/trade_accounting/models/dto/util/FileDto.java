package src.main.java.com.trade_accounting.models.dto.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    private long id;

    private String name;

    private String key;

    private byte[] content;

    private String extension;

    private String placement;

    private String employee;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime uploadDateTime;

    private long productId;

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", extension='" + extension + '\'' +
                ", placement='" + placement + '\'' +
                ", employee='" + employee + '\'' +
                ", uploadDateTime=" + uploadDateTime +
                ", productId=" + productId +
                '}';
    }
}
