package src.test.java.com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import com.trade_accounting.repositories.invoice.InvoicesStatusRepository;
import com.trade_accounting.Stubs.model.invoice.InvoicesStatusStubs;
import com.trade_accounting.utils.mapper.invoice.InvoicesStatusMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoicesStatusServiceImplTest {

    @InjectMocks
    private InvoicesStatusServiceImpl statusService;

    @Mock
    private InvoicesStatusRepository statusRepository;

    @Spy
    private InvoicesStatusMapperImpl statusMapper;

    @Test
    void getAll() {
        when(statusRepository.findAll()).thenReturn(Stream.of(
                InvoicesStatusStubs.getInvoicesStatus(1L),
                InvoicesStatusStubs.getInvoicesStatus(2L),
                InvoicesStatusStubs.getInvoicesStatus(3L)
        ).collect(Collectors.toList()));
        List<InvoicesStatusDto> testData = statusService.getAll();
        assertNotNull(testData, "failure - expected that a list of InvoicesStatusDto not null");
        assertTrue(testData.size() > 0, "failure - expected that a list of InvoicesStatusDto grater than 0");
        testData.forEach(this::dataCorrectnessTesting);
    }

    @Test
    void getById() {
        when(statusRepository.getOne(anyLong())).thenReturn(InvoicesStatusStubs.getInvoicesStatus(1L));
        InvoicesStatusDto testDto = statusService.getById(1L);
        dataCorrectnessTesting(testDto);
    }

    @Test
    void create() {
        when(statusRepository.save(any(InvoicesStatus.class))).thenReturn(InvoicesStatusStubs.getInvoicesStatus(1L));
        InvoicesStatusDto invoicesStatusDto = statusService.create(statusMapper.toDto(InvoicesStatusStubs.getInvoicesStatus(1L)));
        verify(statusRepository).save(any(InvoicesStatus.class));
        dataCorrectnessTesting(invoicesStatusDto);
    }

    @Test
    void update() {
        when(statusRepository.save(any(InvoicesStatus.class))).thenReturn(InvoicesStatusStubs.getInvoicesStatus(1L));
        InvoicesStatusDto invoicesStatusDto = statusService.update(statusMapper.toDto(InvoicesStatusStubs.getInvoicesStatus(1L)));
        verify(statusRepository).save(any(InvoicesStatus.class));
        dataCorrectnessTesting(invoicesStatusDto);
    }

    @Test
    void deleteById() {
        statusService.deleteById(1L);
        verify(statusRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getByName() {
        when(statusRepository.findByStatusName(anyString())).thenReturn(Optional.of(InvoicesStatusStubs.getInvoicesStatus(1L)));
        InvoicesStatusDto invoicesStatusDto = statusService.getByName("Новый1");
        dataCorrectnessTesting(invoicesStatusDto);
    }

    private void dataCorrectnessTesting(InvoicesStatusDto invoicesStatusDto) {
        assertNotNull(invoicesStatusDto, "No data received");
        assertNotNull(invoicesStatusDto.getId(), "Dto have no id");
        assertNotNull(invoicesStatusDto.getStatusName(), "Dto have no statusName");
    }
}