package src.test.java.com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrder;
import com.trade_accounting.models.dto.invoice.InternalOrderDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.invoice.InternalOrderProductRepository;
import com.trade_accounting.repositories.invoice.InternalOrderRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.Stubs.dto.invoice.InternalOrderDtoStubs;
import com.trade_accounting.Stubs.model.invoice.InternalOrderModelStubs;
import com.trade_accounting.utils.mapper.invoice.InternalOrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InternalOrderServiceImplTest {
    @InjectMocks
    private InternalOrderServiceImpl internalOrderService;

    @Mock
    private InternalOrderRepository internalOrderRepository;

    @Mock
    private InternalOrderProductRepository internalOrderProductRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Spy
    private InternalOrderMapper dtoMapper;

    @Test
    void getAll() {
        when(internalOrderRepository.findAll())
                .thenReturn(
                        List.of(
                                InternalOrderModelStubs.getInternalOrder(1L),
                                InternalOrderModelStubs.getInternalOrder(2L),
                                InternalOrderModelStubs.getInternalOrder(3L)
                        ));

        List<InternalOrderDto> internalOrderDtos = internalOrderService.getAll();

        assertEquals(3, internalOrderDtos.size());
    }

    @Test
    void getById() {
        when(internalOrderRepository.getOne(anyLong()))
                .thenReturn(InternalOrderModelStubs.getInternalOrder(1L));

        InternalOrderDto internalOrderDto = internalOrderService.getById(1L);

        assertEquals(1, internalOrderDto.getId());
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
    void deleteById() {
        internalOrderService.deleteById(anyLong());
        verify(internalOrderRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(internalOrderRepository.save(any(InternalOrder.class)))
                .thenReturn(InternalOrderModelStubs.getInternalOrder(1L));

        InternalOrderDto internalOrderDto = internalOrderService
                .create(InternalOrderDtoStubs.getDto(1L));

        assertEquals(1, internalOrderDto.getId());
        verify(internalOrderRepository).save(any(InternalOrder.class));
    }
}