package src.main.java.com.trade_accounting.models.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String index;
    private String country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String another;

    public AddressDto(String index, String city, String street, String house) {
        this.index = index;
        this.city = city;
        this.street = street;
        this.house = house;
    }

}
