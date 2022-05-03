package src.test.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.models.dto.util.OperationsDto;
import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.repositories.util.OperationsRepository;
import com.trade_accounting.utils.mapper.util.OperationsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperationsServiceImplTest {

    @Mock
    private OperationsRepository operationsRepository;

    @Spy
    private OperationsMapper operationsMapper;

    @InjectMocks
    private OperationsServiceImpl operationsService;

    @Test
    void getAll_shouldReturnListOfOperationsDTO() {

        when(operationsRepository.findAll()).thenReturn(
                List.of(ModelStubs.getPayment(1L),
                        ModelStubs.getPayment(2L),
                        ModelStubs.getPayment(3L))
        );

        List<OperationsDto> operations = operationsService.getAll();

        assertNotNull(operations, "failure - list of operations expected");
        assertTrue(operations.size() > 0);
        verify(operationsRepository, times(1)).findAll();

        for (OperationsDto operation : operations) {
            operationsDtoIsCorrectlyInited(operation);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListOperationsDto() {
        when(operationsRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<OperationsDto> operations = operationsService.getAll();

        assertNotNull(operations, "failure - expected that a list of operationDto not null");
        assertEquals(0, operations.size(), "failure - expected that size of list of projectDto equals 0");
        verify(operationsRepository, times(1)).findAll();
    }

    @Test
    void search_shouldReturnListFilledOperationsDto() {

        when(operationsRepository.findAll(SpecificationStubs.getOperationSpecificationStub())).thenReturn(
                List.of(ModelStubs.getPayment(1L),
                        ModelStubs.getPayment(2L),
                        ModelStubs.getPayment(3L))
        );

        List<OperationsDto> operations = operationsService.search(SpecificationStubs.getOperationSpecificationStub());

        assertNotNull(operations, "failure - expected that a list of OperationsDto not null");
        assertTrue(operations.size() > 0, "failure - expected that a list of OperationsDto greater than 0");

        for (OperationsDto operation : operations) {
            operationsDtoIsCorrectlyInited(operation);
        }

    }

    @Test
    void search_shouldReturnEmptyListOperationsDto() {

        when(operationsRepository.findAll(Mockito.<Specification<OperationsAbstract>>any()))
                .thenReturn(new ArrayList<>());

        List<OperationsDto> operations = operationsService
                .search(SpecificationStubs.getOperationSpecificationStub());

        assertNotNull(operations, "failure - expected that a list of operationsDto not null");
        assertEquals(0, operations.size(), "failure - expected that size of list of operationsDto equals 0");

    }

    @Test
    void quickSearch_shouldReturnListFilledOperationsDto() {

        when(operationsRepository.getBySearch(anyString())).thenReturn(
                List.of(ModelStubs.getPayment(1L),
                        ModelStubs.getPayment(2L),
                        ModelStubs.getPayment(3L))
        );

        List<OperationsDto> operations = operationsService.quickSearch(anyString());

        assertNotNull(operations, "failure - expected that a list of OperationsDto not null");
        assertTrue(operations.size() > 0, "failure - expected that a list of OperationsDto greater than 0");

        for (OperationsDto operation : operations) {
            operationsDtoIsCorrectlyInited(operation);
        }

    }

    @Test
    void quickSearch_shouldReturnEmptyListOperationsDto() {

        when(operationsRepository.getBySearch(anyString()))
                .thenReturn(new ArrayList<>());

        List<OperationsDto> operations = operationsService
                .quickSearch(anyString());

        assertNotNull(operations, "failure - expected that a list of operationsDto not null");
        assertEquals(0, operations.size(), "failure - expected that size of list of operationsDto equals 0");

    }

    @Test
    void quickSearchRecycle_shouldReturnListFilledOperationsDto() {

        when(operationsRepository.getBySearchDeleted(anyString())).thenReturn(
                List.of(ModelStubs.getPayment(1L),
                        ModelStubs.getPayment(2L),
                        ModelStubs.getPayment(3L))
        );

        List<OperationsDto> operations = operationsService.quickSearchRecycle(anyString());

        assertNotNull(operations, "failure - expected that a list of OperationsDto not null");
        assertEquals(1, operations.size());

        for (OperationsDto operation : operations) {
            operationsDtoIsCorrectlyInited(operation);
        }

    }

    @Test
    void quickSearchRecycle_shouldReturnEmptyListOperationsDto() {

        when(operationsRepository.getBySearchDeleted(anyString()))
                .thenReturn(new ArrayList<>());

        List<OperationsDto> operations = operationsService
                .quickSearchRecycle(anyString());

        assertNotNull(operations, "failure - expected that a list of operationsDto not null");
        assertEquals(0, operations.size(), "failure - expected that size of list of operationsDto equals 0");

    }

    private void operationsDtoIsCorrectlyInited(OperationsDto operation) {

        assertNotNull(operation.getId());
        assertNotNull(operation.getCompanyId());
        assertNotNull(operation.getDate());
    }

}
