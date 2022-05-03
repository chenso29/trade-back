package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.warehouse.Revenue;
import com.trade_accounting.models.dto.warehouse.RevenueDto;
import com.trade_accounting.repositories.invoice.InvoiceProductRepository;
import com.trade_accounting.repositories.warehouse.AcceptanceProductionRepository;
import com.trade_accounting.repositories.warehouse.AcceptanceRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.RevenueRepository;
import com.trade_accounting.Stubs.dto.warehouse.RevenueDtoStubs;
import com.trade_accounting.utils.mapper.warehouse.RevenueMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RevenueServiceImplTest {


    //Класс не реализован во фронте и де-факто пустой.

    @InjectMocks
    private RevenueServiceImpl revenueService;
    @Mock
    private RevenueRepository revenueRepository;

    @Spy
    private RevenueMapper revenueMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private AcceptanceRepository acceptanceRepository;
    @Mock
    private AcceptanceProductionRepository acceptanceProductionRepository;
    @Mock
    private InvoiceProductRepository invoiceProductRepository;


    @Test
    void getAll_shouldReturnListFilledRevenueDto() {
        when(revenueRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getRevenue(1L),
                                ModelStubs.getRevenue(2L),
                                ModelStubs.getRevenue(3L)
                        ).collect(Collectors.toList())
                );

        List<RevenueDto> revenues = revenueService.getAll();

        assertNotNull(revenues, "failure - expected that a list of revenueDto not null");
        assertTrue(revenues.size() > 0, "failure - excpected that a list of revenueDto grater than 0");

        for(RevenueDto revenueDto : revenues) {
            RevenueDtoIsCorrectlyInited(revenueDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListRevenueDto() {
        when(revenueRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<RevenueDto> revenueDtos = revenueService.getAll();

        assertNotNull(revenueDtos, "failure - expected that a list of revenueDto not null");
        assertEquals(0, revenueDtos.size(), "failure - expected that a list of revenueDto equals 0");
    }

    @Test
    void getById() {
        Optional<Revenue> revenueFromRepo = Optional.of(ModelStubs.getRevenue(1L));

        when(revenueRepository.findById(anyLong()))
                .thenReturn(revenueFromRepo);

        RevenueDto revenueDto = revenueService.getById(1L);

        assertNotNull(revenueDto, "failure - expected that a list of revenueDto not null");
        RevenueDtoIsCorrectlyInited(revenueDto);
    }

    @Test
    void create() {
        revenueService.create(
                RevenueDtoStubs.getDto(1L)
        );

        verify(revenueRepository).save(any(Revenue.class));
    }

    @Test
    void update() {
        revenueService.update(
                RevenueDtoStubs.getDto(1L)
        );

        verify(revenueRepository).save(any(Revenue.class));
    }

    @Test
    void deleteById() {
        revenueService.deleteById(1L);
        verify(revenueRepository).deleteById(1L);
    }

    private void RevenueDtoIsCorrectlyInited(RevenueDto revenueDto) {
        assertNotNull(revenueDto, "failure - fail in passed shipmentProductDto1");
        assertNotNull(revenueDto.getId(), "failure - fail in passed shipmentProductDto2");
        assertNotNull(revenueDto.getItemNumber(), "failure - fail in passed shipmentProductDto3");
        assertNotNull(revenueDto.getProductId(), "failure - fail in passed shipmentProductDto4");
        assertNotNull(revenueDto.getDescription(), "failure - fail in passed shipmentProductDto5");
        assertNotNull(revenueDto.getUnitId(), "failure - fail in passed shipmentProductDto6");
        assertNotNull(revenueDto.getUnitShortName(), "failure - fail in passed shipmentProductDto7");
        assertNotNull(revenueDto.getAcceptanceProductionId(), "failure - fail in passed shipmentProductDto8");
        assertNotNull(revenueDto.getAmountAcceptance(), "failure - fail in passed shipmentProductDto9");
        assertNotNull(revenueDto.getAcceptanceId(), "failure - fail in passed shipmentProductDto10");
        assertNotNull(revenueDto.getIncomingNumberDate(), "failure - fail in passed shipmentProductDto11");
        assertNotNull(revenueDto.getInvoiceProductId(), "failure - fail in passed shipmentProductDto12");
        assertNotNull(revenueDto.getAmountShipment(), "failure - fail in passed shipmentProductDto13");
        assertNotNull(revenueDto.getStartOfPeriodAmount(), "failure - fail in passed shipmentProductDto14");
        assertNotNull(revenueDto.getStartOfPeriodSumOfPrice(), "failure - fail in passed shipmentProductDto15");
        assertNotNull(revenueDto.getEndOfPeriodAmount(), "failure - fail in passed shipmentProductDto16");
        assertNotNull(revenueDto.getEndOfPeriodSumOfPrice(), "failure - fail in passed shipmentProductDto17");
        assertNotNull(revenueDto.getComingAmount(), "failure - fail in passed shipmentProductDto18");
        assertNotNull(revenueDto.getComingSumOfPrice(), "failure - fail in passed shipmentProductDto19");
        assertNotNull(revenueDto.getSpendingAmount(), "failure - fail in passed shipmentProductDto20");
        assertNotNull(revenueDto.getSpendingSumOfPrice(), "failure - fail in passed shipmentProductDto21");

    }
}