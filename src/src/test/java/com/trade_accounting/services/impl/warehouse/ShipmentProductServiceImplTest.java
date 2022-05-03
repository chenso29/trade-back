package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.ShipmentProduct;
import com.trade_accounting.models.dto.warehouse.ShipmentProductDto;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.ShipmentProductRepository;
import com.trade_accounting.Stubs.dto.warehouse.ShipmentProductDtoStubs;
import com.trade_accounting.Stubs.model.warehouse.ShipmentProductModelStubs;
import com.trade_accounting.utils.mapper.warehouse.ShipmentProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShipmentProductServiceImplTest {

    @Mock
    ShipmentProductRepository shipmentProductRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    ShipmentProductMapper shipmentProductMapper;

    @InjectMocks
    ShimpentProductServiceImpl shimpentProductService;

    @Test
    void getAll() {
        when(shipmentProductRepository.findAll()).thenReturn(
                List.of(ShipmentProductModelStubs.getShipmentProduct(1L))
        );

        List<ShipmentProductDto> listInv = shimpentProductService.getAll();
        assertNotNull(listInv, "failure - expected that a list of ShipmentProductDto not null");
        assertEquals(1, listInv.size(), "failure - expected that a list of ShipmentProductDto grater than 0");
    }

    @Test
    void getById(){

        when(shipmentProductRepository.getOne(anyLong()))
                .thenReturn(ShipmentProductModelStubs.getShipmentProduct(1L));

        ShipmentProductDto shipmentProductDto = shimpentProductService.getById(1L);

        ShipmentProductDtoIsCorrectlyInited(shipmentProductDto);
    }

    @Test
    void create(){

            saveOrUpdate();

    }

    @Test
    void update(){
        saveOrUpdate();
    }

    @Test
    void deleteById(){
        shipmentProductRepository.deleteById(anyLong());
        verify(shipmentProductRepository).deleteById(anyLong());
    }

    private void ShipmentProductDtoIsCorrectlyInited(ShipmentProductDto shipmentProductDto) {
        assertNotNull(shipmentProductDto, "failure - fail in passed shipmentProductDto");
        assertNotNull(shipmentProductDto.getId(), "failure - fail in field 'id' into shipmentProductDto");
        assertNotNull(shipmentProductDto.getAmount(), "failure - fail in field 'amount' into shipmentProductDto");
        assertNotNull(shipmentProductDto.getPrice(), "failure - fail in field 'price' into shipmentProductDto");
        assertNotNull(shipmentProductDto.getProductId(), "failure - fail in field 'productId' into shipmentProductDto");
    }

    private void saveOrUpdate() {
        when(shipmentProductRepository.save(any(ShipmentProduct.class))).thenReturn(ShipmentProductModelStubs.getShipmentProduct(1L));
        ShipmentProductDto shipmentProductDto = shimpentProductService.create(ShipmentProductDtoStubs.getDto(1L));
        assertEquals(1,shipmentProductDto.getId());
        verify(shipmentProductRepository).save(any(ShipmentProduct.class));
    }

//    when(retailSalesRepository.save(any(RetailSales.class))).thenReturn(RetailSalesModelStubs.getRetailSales(1L));
//    RetailSalesDto retailSalesDto = retailSalesService.create(RetailSalesDtoStubs.getDto(1L));
//    assertEquals(1,retailSalesDto.getId());
//    verify(retailSalesRepository).save(any(RetailSales.class));

}
