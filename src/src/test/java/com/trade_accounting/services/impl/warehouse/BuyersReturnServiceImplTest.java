package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import com.trade_accounting.repositories.warehouse.BuyersReturnRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.warehouse.BuyersReturnDtoStubs;;
import com.trade_accounting.utils.mapper.warehouse.BuyersReturnMapperImpl;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuyersReturnServiceImplTest {

    @InjectMocks
    private BuyersReturnServiceImpl buyersReturnService;

    @Mock
    private BuyersReturnRepository buyersReturnRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ContractorRepository contractorRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Spy
    private BuyersReturnMapperImpl buyersReturnMapper;

    @Test
    void getAll_shouldReturnListFilledBuyersReturnDto() {
        when(buyersReturnRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getBuyersReturn(1L),
                                ModelStubs.getBuyersReturn(2L),
                                ModelStubs.getBuyersReturn(3L)
                        ).collect(Collectors.toList())
                );

        List<BuyersReturnDto> buyersReturnDtos = buyersReturnService.getAll();

        assertNotNull(buyersReturnDtos, "Failure - expected that list of buyersReturnDtos not null");
        assertEquals(3, buyersReturnDtos.size(), "failure - expected that size of list of buyersReturnDtos equals 3");
        verify(buyersReturnRepository).findAll();

        for (BuyersReturnDto buyersReturnDto : buyersReturnDtos) {
            buyersReturnDtoIsCorrectlyInited(buyersReturnDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListBuyersReturnDto() {
        when(buyersReturnRepository.findAll())
                .thenReturn(
                        new ArrayList<>()
                );

        List<BuyersReturnDto> buyersReturnDtos = buyersReturnService.getAll();

        assertNotNull(buyersReturnDtos, "Failure - expected that list of buyersReturnDtos not null");
        assertEquals(0, buyersReturnDtos.size(), "failure - expected that size of list of list of buyersReturnDtos equals 0");
    }

    @Test
    void getById_shouldReturnFilledBuyersReturnDto() {
        when(buyersReturnRepository.findById(1L))
                .thenReturn(Optional.of(ModelStubs.getBuyersReturn(1L)));

        BuyersReturnDto buyersReturnDto = buyersReturnService.getById(1L);
        buyersReturnDtoIsCorrectlyInited(buyersReturnDto);
    }

    @Test
    void getByContractorId_shouldReturnListFilledBuyersReturnDto() {
        when(buyersReturnRepository.findByContractorId(anyLong()))
                .thenReturn(List.of(BuyersReturnDtoStubs.getBuyersReturnDto(1L)));

        List<BuyersReturnDto> buyersReturnDtoList = buyersReturnService.getByContractorId(1L);

        for (BuyersReturnDto buyersReturnDto : buyersReturnDtoList) {
            buyersReturnDtoIsCorrectlyInited(buyersReturnDto);
        }
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        buyersReturnService.create(BuyersReturnDtoStubs.getBuyersReturnDto(1L));

        verify(buyersReturnRepository).save(any(BuyersReturn.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        buyersReturnService.update(BuyersReturnDtoStubs.getBuyersReturnDto(1L));

        verify(buyersReturnRepository).save(any(BuyersReturn.class));
    }

    @Test
    void deleteById_shouldPassInstructionSuccessfulDelete() {
        buyersReturnService.deleteById(1L);

        verify(buyersReturnRepository).deleteById(anyLong());
    }

    @Test
    void search_shouldReturnListFilledBuyersReturnDto() {
        when(buyersReturnRepository.findAll(Mockito.<Specification<BuyersReturn>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getBuyersReturn(1L),
                                ModelStubs.getBuyersReturn(2L),
                                ModelStubs.getBuyersReturn(3L)
                        ).collect(Collectors.toList())
                );

        List<BuyersReturnDto> buyersReturnDtos = buyersReturnService
                .search(SpecificationStubs.getBuyersReturnSpecificationStub());

        assertNotNull(buyersReturnDtos, "failure - expected that a list of buyersReturnDtos not null");
        assertTrue(buyersReturnDtos.size() > 0, "failure - expected that a list of buyersReturnDtos greater than 0");

        for (BuyersReturnDto buyersReturnDto : buyersReturnDtos) {
            buyersReturnDtoIsCorrectlyInited(buyersReturnDto);
        }
    }

    @Test
    void search_shouldReturnEmptyListBuyersReturnDto() {
        when(buyersReturnRepository.findAll(Mockito.<Specification<BuyersReturn>>any()))
                .thenReturn(new ArrayList<>());

        List<BuyersReturnDto> buyersReturnDtos = buyersReturnService
                .search(SpecificationStubs.getBuyersReturnSpecificationStub());

        assertNotNull(buyersReturnDtos, "failure - expected that a list of buyersReturnDtos not null");
        assertEquals(0, buyersReturnDtos.size(), "failure - expected that size of list of buyersReturnDtos equals 0");
    }

    private void buyersReturnDtoIsCorrectlyInited(BuyersReturnDto buyersReturnDto) {
        assertNotNull(buyersReturnDto, "failure - fail in passed buyersReturnDto");
        assertNotNull(buyersReturnDto.getId(), "failure - fail in field 'id' into buyersReturnDto");
        assertNotNull(buyersReturnDto.getDate(), "failure - fail in field 'date' into buyersReturnDto");
        assertNotNull(buyersReturnDto.getWarehouseId(), "failure - fail in field 'warehouseId' into buyersReturnDto");
        assertNotNull(buyersReturnDto.getContractorId(), "failure - fail in field 'contractorId' into buyersReturnDto");
        assertNotNull(buyersReturnDto.getCompanyId(), "failure - fail in field 'companyId' into buyersReturnDto");
        assertNotNull(buyersReturnDto.getSum(), "failure - fail in field 'sum' into buyersReturnDto");
        assertNotNull(buyersReturnDto.getComment(), "failure - fail in field 'comment' into buyersReturnDto");
    }
}
