package src.test.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.production.OrdersOfProductionDtoStubs;
import com.trade_accounting.models.dto.production.OrdersOfProductionDto;
import com.trade_accounting.models.entity.production.OrdersOfProduction;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.production.OrdersOfProductionRepository;
import com.trade_accounting.repositories.production.TechnicalCardRepository;
import com.trade_accounting.utils.mapper.production.OrdersOfProductionMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdersOfProductionServiceImplTest {

    @Spy
    private OrdersOfProductionMapper ordersOfProductionMapper;

    @Mock
    private OrdersOfProductionRepository ordersOfProductionRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private TechnicalCardRepository technicalCardRepository;

    @InjectMocks
    private OrdersOfProductionServiceImpl ordersOfProductionService;

    @Test
    void searchBySpec_shouldReturnListFilledOrdersOfProductionDto() {

        when(ordersOfProductionRepository.findAll(SpecificationStubs.getOrdersOfProductionStub())).thenReturn(
                List.of(ModelStubs.getOrdersOfProduction(1L),
                        ModelStubs.getOrdersOfProduction(2L),
                        ModelStubs.getOrdersOfProduction(3L))
        );

        List<OrdersOfProductionDto> orders = ordersOfProductionService.search(SpecificationStubs.getOrdersOfProductionStub());

        assertNotNull(orders, "failure - expected that a list of OrdersOfProductionDto not null");
        assertTrue(orders.size() > 0, "failure - expected that a list of OrdersOfProductionDto greater than 0");

        for (OrdersOfProductionDto order : orders) {
            ordersOfProductionDtoIsCorrectlyInited(order);
        }

    }

    @Test
    void getAll() {

        when(ordersOfProductionRepository.findAll()).thenReturn(
                List.of(ModelStubs.getOrdersOfProduction(1L),
                        ModelStubs.getOrdersOfProduction(2L),
                        ModelStubs.getOrdersOfProduction(3L))
        );

        List<OrdersOfProductionDto> orders = ordersOfProductionService.getAll();

        assertNotNull(orders, "failure - list of orders expected");
        assertTrue(orders.size() > 0);
        verify(ordersOfProductionRepository, times(1)).findAll();

        for (OrdersOfProductionDto order : orders) {
            ordersOfProductionDtoIsCorrectlyInited(order);
        }
    }

    @Test
    void getById_shouldReturnFilledProductionDto() {
        when(ordersOfProductionRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getOrdersOfProduction(1L));

        OrdersOfProductionDto ordersOfProductionDto = ordersOfProductionService.getById(anyLong());

        assertNotNull(ordersOfProductionDto, "Failure - expected that list of orders not null");
        verify(ordersOfProductionRepository).getOne(anyLong());
    }

    @Test
    void deleteById_shouldPassInstructionSuccessfulDelete() {
        ordersOfProductionService.deleteById(1L);
        verify(ordersOfProductionRepository).deleteById(anyLong());
    }

    @Test
    void searchByString_shouldReturnCorrectSizeListFilledOrdersOfProductionDto() {
        when(ordersOfProductionRepository.findAll()).thenReturn(
                List.of(ModelStubs.getOrdersOfProduction(1L),
                        ModelStubs.getOrdersOfProduction(2L),
                        ModelStubs.getOrdersOfProduction(3L))
        );
        when(ordersOfProductionRepository.search(anyString())).thenReturn(
                List.of(ModelStubs.getOrdersOfProduction(1L))
        );

        List<OrdersOfProductionDto> ordersFoundByEmptyString = ordersOfProductionService.search("");
        List<OrdersOfProductionDto> ordersFoundByNullString = ordersOfProductionService.search("null");
        List<OrdersOfProductionDto> ordersFoundByString = ordersOfProductionService.search("searchTerm");

        assertNotNull(ordersFoundByEmptyString, "failure - expected that a list of OrdersOfProductionDto not null");
        assertNotNull(ordersFoundByNullString, "failure - expected that a list of OrdersOfProductionDto not null");
        assertNotNull(ordersFoundByString, "failure - expected that a list of OrdersOfProductionDto not null");

        assertEquals(3, ordersFoundByEmptyString.size(), "failure - expected that a list of OrdersOfProductionDto greater than 0");
        assertEquals(3, ordersFoundByNullString.size(), "failure - expected that a list of OrdersOfProductionDto greater than 0");
        assertEquals(1, ordersFoundByString.size(), "failure - expected that a list of OrdersOfProductionDto greater than 0");


        List<List<OrdersOfProductionDto>> orderLists = List.of(ordersFoundByEmptyString, ordersFoundByNullString, ordersFoundByString);
        for (List<OrdersOfProductionDto> orderList : orderLists) {
            for (OrdersOfProductionDto order : orderList) {
                ordersOfProductionDtoIsCorrectlyInited(order);
            }
        }

    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        ordersOfProductionService.create(
                OrdersOfProductionDtoStubs.getOrdersOfProductionDto(1L)
        );

        verify(ordersOfProductionRepository).save(any(OrdersOfProduction.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        ordersOfProductionService.create(
                OrdersOfProductionDtoStubs.getOrdersOfProductionDto(1L)
        );

        verify(ordersOfProductionRepository).save(any(OrdersOfProduction.class));
    }

    private void saveOrUpdate() {

        when(ordersOfProductionRepository.save(any(OrdersOfProduction.class))).thenReturn(ModelStubs.getOrdersOfProduction(1L));
        when(technicalCardRepository.getTechnicalCardById(anyLong())).thenReturn(ModelStubs.getTechnicalCard(1L));
        when(companyRepository.getCompaniesById(anyLong())).thenReturn(ModelStubs.getCompany(anyLong()));

        OrdersOfProductionDto ordersOfProductionDto = ordersOfProductionService.create(OrdersOfProductionDtoStubs.getOrdersOfProductionDto(1L));

        assertEquals(1, ordersOfProductionDto.getId());
        verify(ordersOfProductionRepository).save(any(OrdersOfProduction.class));
    }

    private void ordersOfProductionDtoIsCorrectlyInited(OrdersOfProductionDto order) {

        assertNotNull(order.getId());
        assertNotNull(order.getDate());
        assertNotNull(order.getCompanyId());
        assertNotNull(order.getId());
        assertNotNull(order.getTechnicalCardId());
        assertNotNull(order.getVolume());
        assertNotNull(order.getProduce());
        assertNotNull(order.getPlannedProductionDate());
        assertNotNull(order.getIsSent());
        assertNotNull(order.getIsPrint());
        assertNotNull(order.getComment());


    }
}