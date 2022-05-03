package src.test.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.entity.retail.RetailReturn;
import com.trade_accounting.models.dto.retail.RetailReturnDto;
import com.trade_accounting.repositories.retail.RetailReturnRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.Stubs.dto.retail.RetailReturnDtoStubs;
import com.trade_accounting.Stubs.model.retail.RetailReturnModelStubs;
import com.trade_accounting.utils.mapper.retail.RetailReturnMapper;
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
public class RetailReturnServiceImplTest {

    @Mock
    RetailReturnRepository retailReturnRepository;

    @Mock
    RetailStoreRepository retailStoreRepository;


    @InjectMocks
    RetailReturnServiceImpl retailReturnService;

    @Spy
    RetailReturnMapper retailReturnMapper;

    @Test
    void getAll() {
        when(retailReturnRepository.findAll()).thenReturn(
                List.of(RetailReturnModelStubs.getRetailReturn(1L))
        );

        List<RetailReturnDto> listInv = retailReturnService.getAll();
        assertEquals(1, listInv.size());
    }

    @Test
    void getById() {
        when(retailReturnRepository.findById(anyLong())).thenReturn(java.util.Optional.of(RetailReturnModelStubs.getRetailReturn(1L)));

        RetailReturnDto retailReturnDto = retailReturnService.getById(1L);
        assertEquals(1, retailReturnDto.getId());
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        saveOrUpdate();
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        saveOrUpdate();
    }

    @Test
    void delete_update_shouldPassInstructionsSuccessfulDelete() {
        retailReturnService.deleteById(anyLong());
        verify(retailReturnRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailReturnRepository.save(any(RetailReturn.class))).thenReturn(RetailReturnModelStubs.getRetailReturn(1L));

        RetailReturnDto retailReturnDto = retailReturnService.create(RetailReturnDtoStubs.getDto(1L));
        assertEquals(1,retailReturnDto.getId());
        verify(retailReturnRepository).save(any(RetailReturn.class));
    }
}
