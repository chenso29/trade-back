package src.main.java.com.trade_accounting.models.entity.util;

import com.trade_accounting.models.entity.warehouse.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "file")
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String extension;

    @Column
    private String placement;

    @Column
    private String employee;

    @Column(unique = true)
    private String key;

    @Column(name = "upload_date_time")
    private LocalDateTime uploadDateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    public File(Long id,
                String name,
                String extension,
                String placement,
                String employee,
                String key,
                LocalDateTime uploadDateTime) {
        this.id = id;
        this.name = name;
        this.extension = extension;
        this.placement = placement;
        this.employee = employee;
        this.key = key;
        this.uploadDateTime = uploadDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(getId(), file.getId()) &&
                Objects.equals(getName(), file.getName()) &&
                Objects.equals(getExtension(), file.getExtension()) &&
                Objects.equals(getPlacement(), file.getPlacement()) &&
                Objects.equals(getEmployee(), file.getEmployee()) &&
                Objects.equals(getKey(), file.getKey()) &&
                Objects.equals(getUploadDateTime(), file.getUploadDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getExtension(), getPlacement(), getEmployee(), getKey(), getUploadDateTime());
    }
}