package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.AddressDto;

/**
 * @author Andrey Melnikov
 * @since 10.08.2021
 */

public class AddressDtoStubs {
    public static AddressDto getAddressDto(Long id){
        return AddressDto.builder()
                .id(id)
                .index("index" + id)
                .country("country" + id)
                .region("region" + id)
                .city("city" + id)
                .street("street" + id)
                .house("house" + id)
                .apartment("apartment" + id)
                .another("another" + id)
                .build();
    }
}
