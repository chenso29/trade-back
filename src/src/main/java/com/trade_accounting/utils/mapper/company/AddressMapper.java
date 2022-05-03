package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Address;
import com.trade_accounting.models.dto.company.AddressDto;
import org.mapstruct.Mapper;

/**
 * @author Andrey Melnikov
 * @since 10.08.2021
 */
@Mapper(componentModel = "spring")
public interface AddressMapper {

    default AddressDto toDto(Address address) {
        if (address == null) {
            return null;
        }

        AddressDto.AddressDtoBuilder addressDto = AddressDto.builder();

        addressDto.id(address.getId());
        addressDto.index(address.getIndex());
        addressDto.country(address.getCountry());
        addressDto.region(address.getRegion());
        addressDto.city(address.getCity());
        addressDto.street(address.getStreet());
        addressDto.house(address.getHouse());
        addressDto.apartment(address.getApartment());
        addressDto.another(address.getAnother());

        return addressDto.build();
    }

    default Address toModel(AddressDto address) {
        if (address == null) {
            return null;
        }

        Address.AddressBuilder address1 = Address.builder();

        address1.id(address.getId());
        address1.index(address.getIndex());
        address1.country(address.getCountry());
        address1.region(address.getRegion());
        address1.city(address.getCity());
        address1.street(address.getStreet());
        address1.house(address.getHouse());
        address1.apartment(address.getApartment());
        address1.another(address.getAnother());

        return address1.build();
    }
}
