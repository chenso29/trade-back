package src.test.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.models.entity.util.BonusProgram;
import com.trade_accounting.models.dto.util.BonusProgramDto;
import com.trade_accounting.repositories.util.BonusProgramRepository;
import com.trade_accounting.repositories.company.ContractorGroupRepository;
import com.trade_accounting.Stubs.dto.util.BonusProgramDtoStubs;
import com.trade_accounting.Stubs.model.util.BonusProgramModelStubs;
import com.trade_accounting.utils.mapper.util.BonusProgramMapper;
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
public class BonusProgramServiceImplTest {

    @Mock
    private BonusProgramRepository bonusProgramRepository;

    @Mock
    private ContractorGroupRepository contractorGroupRepository;

    @InjectMocks
    private BonusProgramServiceImpl bonusProgramService;

    @Spy
    private BonusProgramMapper bonusProgramMapper;

    @Test
    void getAll() {

        when(bonusProgramRepository.findAll()).thenReturn(
                List.of(
                        BonusProgramModelStubs.getBonusProgram(1L),
                        BonusProgramModelStubs.getBonusProgram(2L)
                )
        );

        List<BonusProgramDto> bonusProgramDtos = bonusProgramService.getAll();
        assertEquals(2, bonusProgramDtos.size());
    }

    @Test
    void getById() {

        when(bonusProgramRepository.findById(anyLong()))
                .thenReturn(Optional.of(BonusProgramModelStubs.getBonusProgram(1L)));

        BonusProgramDto bonusProgramDto = bonusProgramService.getById(1L);

        assertEquals(1, bonusProgramDto.getId());
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
        bonusProgramService.deleteById(anyLong());
        verify(bonusProgramRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(bonusProgramRepository.save(any(BonusProgram.class)))
                .thenReturn(BonusProgramModelStubs.getBonusProgram(1L));

        BonusProgramDto bonusProgramDto = bonusProgramService
                .create(BonusProgramDtoStubs.getDto(1L));

        assertEquals(1, bonusProgramDto.getId());
        verify(bonusProgramRepository).save(any(BonusProgram.class));
    }
}
