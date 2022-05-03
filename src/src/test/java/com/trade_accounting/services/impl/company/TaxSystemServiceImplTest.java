package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.TaxSystem;
import com.trade_accounting.models.dto.company.TaxSystemDto;
import com.trade_accounting.repositories.company.TaxSystemRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.company.TaxSystemDtoStubs;
import com.trade_accounting.utils.mapper.company.TaxSystemMapper;
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
class TaxSystemServiceImplTest {

    @Mock
    private TaxSystemRepository taxSystemRepository;

    @Spy
    private TaxSystemMapper taxSystemMapper;

    @InjectMocks
    private TaxSystemServiceImpl taxSystemService;

    @Test
    void getAll_shouldReturnListFilledTaxSystemDto() {
        when(taxSystemRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getTaxSystem(1L),
                                ModelStubs.getTaxSystem(1L),
                                ModelStubs.getTaxSystem(1L)
                        )
                                .collect(Collectors.toList())
                );

        List<TaxSystemDto> taxSystems = taxSystemService.getAll();

        assertNotNull(taxSystems, "Failure - expected that list of taxSystem not null");
        assertTrue(taxSystems.size() > 0, "failure - expected that size of list of taxSystem greater than 0");

        for (TaxSystemDto taxSystemDto : taxSystems) {
            taxSystemDtoIsCorrectlyInited(taxSystemDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListTaxSystemDto() {
        when(taxSystemRepository.findAll())
                .thenReturn(
                        new ArrayList<>()
                );

        List<TaxSystemDto> taxSystems = taxSystemService.getAll();

        assertNotNull(taxSystems, "Failure - expected that list of taxSystem not null");
        assertEquals(0, taxSystems.size(), "failure - expected that size of list of taxSystem equals 0");
    }

    @Test
    void getById_shouldReturnFilledTaxSystemDto() {
        Optional<TaxSystem> taxSystemFromRepo =
                Optional.of(ModelStubs.getTaxSystem(1L));

        when(taxSystemRepository.findById(anyLong()))
                .thenReturn(taxSystemFromRepo);

        TaxSystemDto taxSystemDto = taxSystemService.getById(1L);

        taxSystemDtoIsCorrectlyInited(taxSystemDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        taxSystemService.create(TaxSystemDtoStubs.getTaxSystemDto(1L));

        verify(taxSystemRepository).save(any(TaxSystem.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        taxSystemService.update(TaxSystemDtoStubs.getTaxSystemDto(1L));

        verify(taxSystemRepository).save(any(TaxSystem.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        taxSystemService.deleteById(1L);

        verify(taxSystemRepository).deleteById(anyLong());
    }

    private void taxSystemDtoIsCorrectlyInited(TaxSystemDto taxSystemDto) {
        assertNotNull(taxSystemDto, "Fail in passed taxSystem");
        assertNotNull(taxSystemDto.getId(), "Fail in field 'id' of taxSystem");
        assertNotNull(taxSystemDto.getName(), "Fail in field 'name' of taxSystem");
        assertNotNull(taxSystemDto.getSortNumber(), "Fail in field 'sortNumber' of taxSystem");
    }
}