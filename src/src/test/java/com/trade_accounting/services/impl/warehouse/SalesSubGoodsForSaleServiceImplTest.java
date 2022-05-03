package src.test.java.com.trade_accounting.services.impl.warehouse;


import com.trade_accounting.Stubs.dto.warehouse.SalesSubGoodsForSaleDtoStubs;
import com.trade_accounting.Stubs.model.warehouse.SalesSubGoodsForSaleModelStubs;
import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import com.trade_accounting.repositories.warehouse.SalesSubGoodsForSaleRepository;
import com.trade_accounting.utils.mapper.warehouse.SalesSubGoodsForSaleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SalesSubGoodsForSaleServiceImplTest {

    @Mock
    SalesSubGoodsForSaleRepository salesSubGoodsForSaleRepository;

    @InjectMocks
    SalesSubGoodsForSaleServiceImpl salesSubGoodsForSaleService;

    @Spy
    SalesSubGoodsForSaleMapper salesSubGoodsForSaleMapper;

    @Test
    void getAll() {
        when(salesSubGoodsForSaleRepository.findAll()).thenReturn(
                List.of(SalesSubGoodsForSaleModelStubs.getSalesSubGoodsForSale(1L))
        );

        List<SalesSubGoodsForSaleDto> listInv = salesSubGoodsForSaleService.getAll();
        assertNotNull(listInv, "failure - expected that a list of ShipmentProductDto not null");
        assertEquals(1, listInv.size(), "failure - expected that a list of ShipmentProductDto grater than 0");
    }

    @Test
    void getById() {

        Optional<SalesSubGoodsForSale> salesSubGoodsForSale = Optional.of(SalesSubGoodsForSaleModelStubs.getSalesSubGoodsForSale(1L));

        when(salesSubGoodsForSaleRepository.findById(anyLong())).thenReturn(salesSubGoodsForSale);

        SalesSubGoodsForSaleDto salesSubGoodsForSaleDto = salesSubGoodsForSaleService.getById(1L);
        assertEquals(1L, salesSubGoodsForSaleDto.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById(){
        salesSubGoodsForSaleRepository.deleteById(anyLong());
        verify(salesSubGoodsForSaleRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(salesSubGoodsForSaleRepository.save(any(SalesSubGoodsForSale.class))).thenReturn(SalesSubGoodsForSaleModelStubs.getSalesSubGoodsForSale(1L));
        SalesSubGoodsForSaleDto salesSubGoodsForSaleDto = salesSubGoodsForSaleService.create(SalesSubGoodsForSaleDtoStubs.getDto(1L));
        assertEquals(1, salesSubGoodsForSaleDto.getId());
        verify(salesSubGoodsForSaleRepository).save(any(SalesSubGoodsForSale.class));
    }
}
