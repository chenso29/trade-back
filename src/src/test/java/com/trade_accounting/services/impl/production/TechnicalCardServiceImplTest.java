package src.test.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.dto.production.TechnicalCardDto;
import com.trade_accounting.repositories.production.TechnicalCardRepository;
import com.trade_accounting.Stubs.dto.production.TechnicalCardDtoStubs;
import com.trade_accounting.Stubs.model.production.TechnicalCardModelStubs;
import com.trade_accounting.services.interfaces.production.TechnicalCardGroupService;
import com.trade_accounting.services.interfaces.production.TechnicalCardProductionService;
import com.trade_accounting.utils.mapper.production.TechnicalCardGroupMapper;
import com.trade_accounting.utils.mapper.production.TechnicalCardMapper;
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
public class TechnicalCardServiceImplTest {

    @Mock
    private TechnicalCardRepository technicalCardRepository;

    @Mock
    private TechnicalCardProductionService technicalCardProductionService;

    @Spy
    private TechnicalCardMapper technicalCardMapper;

    @Spy
    private TechnicalCardGroupMapper technicalCardGroupMapper;

    @Mock
    private TechnicalCardGroupService technicalCardGroupService;

    @InjectMocks
    private TechnicalCardServiceImpl technicalCardService;

    @Test
    public void getAll(){
            when(technicalCardRepository.findAll())
                .thenReturn(List.of(
                        TechnicalCardModelStubs.getTechnicalCard(1L),
                        TechnicalCardModelStubs.getTechnicalCard(2L),
                        TechnicalCardModelStubs.getTechnicalCard(3L)
                ));
        List<TechnicalCardDto> technicalCardDtos = technicalCardService.getAll();

        assertEquals(3,technicalCardDtos.size());
    }

    @Test
    public void create() {
        saveOrUpdate();
    }

    @Test
    public void update() {
        saveOrUpdate();
    }

    @Test
    public void getById() {
        when(technicalCardRepository.getOne(anyLong()))
                .thenReturn(TechnicalCardModelStubs.getTechnicalCard(1L));

        TechnicalCardDto technicalCardDto = technicalCardService.getById(1L);

        assertEquals(1, technicalCardDto.getId());
    }

    @Test
    public void deleteById() {
        technicalCardService.deleteById(anyLong());
        verify(technicalCardRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(technicalCardRepository.save(any(TechnicalCard.class)))
                .thenReturn(TechnicalCardModelStubs.getTechnicalCard(1L));

        TechnicalCardDto technicalCardDto = technicalCardService
                .create(TechnicalCardDtoStubs.getDto(1L));

        assertEquals(1, technicalCardDto.getId());
        verify(technicalCardRepository).save(any(TechnicalCard.class));
    }
}
