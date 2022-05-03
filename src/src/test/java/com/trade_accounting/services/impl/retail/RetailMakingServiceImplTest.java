package src.test.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.Stubs.dto.retail.RetailMakingDtoStubs;
import com.trade_accounting.Stubs.model.retail.RetailMakingModelStubs;
import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.retail.RetailMaking;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.retail.RetailMakingRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.utils.mapper.retail.RetailMakingMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RetailMakingServiceImplTest {

    @Mock
    private RetailMakingRepository retailMakingRepository;

    @Mock
    private RetailStoreRepository retailStoreRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private RetailMakingServiceImpl retailMakingService;

    @Spy
    private RetailMakingMapper retailMakingMapper;

    @Test
    void getAll() {
        when(retailMakingRepository.findAll()).thenReturn(
                List.of(
                        RetailMakingModelStubs.getRetailMakingModelStub(1L)
                )
        );
        List<RetailMakingDto> list = retailMakingService.getAll();
        assertEquals(1, list.size());
    }

    @Test
    void getById() {
        when(retailMakingRepository.findById(anyLong())).
                thenReturn(Optional.of(RetailMakingModelStubs.getRetailMakingModelStub(1L)));

        RetailMakingDto retailMakingDto = retailMakingService.getById(1L);

        assertEquals(1, retailMakingDto.getId());
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
        retailMakingService.deleteById(anyLong());
        verify(retailMakingRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailStoreRepository.findById(anyLong())).thenReturn(Optional.of(new RetailStore()));
        when(companyRepository.getCompaniesById(anyLong())).thenReturn(new Company());
        when(retailMakingRepository.save(any(RetailMaking.class))).
                thenReturn(RetailMakingModelStubs.getRetailMakingModelStub(1L));

        RetailMakingDto retailMakingDto = retailMakingService.create(RetailMakingDtoStubs.getDto(1L));
        assertEquals(1, retailMakingDto.getId());
        verify(retailMakingRepository).save(any(RetailMaking.class));
    }
}

