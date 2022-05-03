package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.Address;
import com.trade_accounting.models.dto.company.AddressDto;
import com.trade_accounting.repositories.company.AddressRepository;
import com.trade_accounting.Stubs.dto.company.AddressDtoStubs;
import com.trade_accounting.Stubs.model.company.AddressModelStubs;
import com.trade_accounting.utils.mapper.company.AddressMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

/**
 * @author Andrey Melnikov
 * @since 10.08.2021
 */

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Spy
    private AddressMapper mapper;

    @Test
    void getAll(){
        when(addressRepository.findAll())
                .thenReturn(Stream.of(
                        AddressModelStubs.getAddress(1L),
                        AddressModelStubs.getAddress(2L),
                        AddressModelStubs.getAddress(3L)
                ).collect(Collectors.toList()));

        List<AddressDto> dtoList = addressService.getAll();

        Assertions.assertNotNull(dtoList, "Error - результат метода не должен быть равен null!");
        Assertions.assertEquals(3L, dtoList.size(), "Error - размер списка как результат метода ожидается равным - 3");
    }

    @Test
    void getById(){
        when(addressRepository.getOne(anyLong()))
                .thenReturn(AddressModelStubs.getAddress(1L));

        AddressDto addressDto = addressService.getById(6L);

        Assertions.assertEquals(1, addressDto.getId());
    }

    @Test
    void create(){
        when(addressRepository.save(any(Address.class)))
                .thenReturn(AddressModelStubs.getAddress(1L));

        AddressDto addressDto = addressService.create(AddressDtoStubs.getAddressDto(6L));

        Assertions.assertEquals(1L, addressDto.getId());
        verify(addressRepository).save(any());
    }

    @Test
    void update(){
        when(addressRepository.save(any(Address.class)))
                .thenReturn(AddressModelStubs.getAddress(1L));

        AddressDto addressDto = addressService.create(AddressDtoStubs.getAddressDto(6L));

        Assertions.assertEquals(1L, addressDto.getId());
        verify(addressRepository).save(any());
    }

    @Test
    void deleteById(){
        addressService.deleteById(1L);
        verify(addressRepository).deleteById(any());
    }
}
