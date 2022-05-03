package src.test.java.com.trade_accounting.services.impl.retail;


import com.trade_accounting.models.entity.retail.RetailOperationWithPoints;
import com.trade_accounting.models.dto.retail.RetailOperationWithPointsDto;
import com.trade_accounting.repositories.util.BonusProgramRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.util.FileRepository;
import com.trade_accounting.repositories.retail.RetailOperationWithPointsRepository;
import com.trade_accounting.repositories.util.TaskRepository;
import com.trade_accounting.Stubs.dto.retail.RetailOperationWithPointsDtoStubs;
import com.trade_accounting.Stubs.model.retail.RetailOperationWithPointsModelStubs;
import com.trade_accounting.utils.mapper.retail.RetailOperationWithPointsMapper;
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
public class RetailOperationWithPointsServiceImplTest {

    @Mock
    private RetailOperationWithPointsRepository retailOperationWithPointsRepository;

    @Mock
    private  BonusProgramRepository bonusProgramRepository;

    @Mock
    private  ContractorRepository contractorRepository;

    @Mock
    private  TaskRepository taskRepository;

    @Mock
    private  FileRepository fileRepository;

    @InjectMocks
    private RetailOperationWithPointsServiceImpl retailOperationWithPointsService;

    @Spy
    private RetailOperationWithPointsMapper retailOperationWithPointsMapper;

    @Test
    void getAll() {

        when(retailOperationWithPointsRepository.findAll()).thenReturn(
                List.of(
                        RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(1L),
                        RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(2L)
                )
        );

        List<RetailOperationWithPointsDto> bonusProgramDtos = retailOperationWithPointsService.getAll();
        assertEquals(2, bonusProgramDtos.size());
    }

    @Test
    void getById() {

        when(retailOperationWithPointsRepository.findById(anyLong()))
                .thenReturn(Optional.of(RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(1L)));

        RetailOperationWithPointsDto retailOperationWithPointsDto = retailOperationWithPointsService.getById(1L);

        assertEquals(1, retailOperationWithPointsDto.getId());
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
        retailOperationWithPointsService.deleteById(anyLong());
        verify(retailOperationWithPointsRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailOperationWithPointsRepository.save(any(RetailOperationWithPoints.class)))
                .thenReturn(RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(1L));

        RetailOperationWithPointsDto retailOperationWithPointsDto = retailOperationWithPointsService
                .create(RetailOperationWithPointsDtoStubs.getDto(1L));

        assertEquals(1, retailOperationWithPointsDto.getId());
        verify(retailOperationWithPointsRepository).save(any(RetailOperationWithPoints.class));
    }

}
