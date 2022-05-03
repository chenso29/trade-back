package src.test.java.com.trade_accounting.Stubs.model.company;

import com.trade_accounting.models.entity.company.Address;

/**
 * @author Andrey Melnikov
 * @since 10.08.2021
 */

public class AddressModelStubs {

    public static Address getAddress(Long id){
        return Address.builder()
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
