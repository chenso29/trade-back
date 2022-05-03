package src.test.java.com.trade_accounting.services.impl.indicators;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.indicators.Audit;
import com.trade_accounting.repositories.indicators.AuditRepository;
import com.trade_accounting.services.impl.indicators.audit.AuditServiceImpl;
import com.trade_accounting.utils.mapper.indicators.AuditMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    @Mock
    AuditServiceImpl auditService;

    @Mock
    AuditRepository auditRepository;

    @Autowired
    AuditMapper mapper;

    @Test
    void getAll() {
        List<Audit> repoTestStub = Stream.of(
                ModelStubs.getAudit(1L),
                ModelStubs.getAudit(2L),
                ModelStubs.getAudit(3L),
                ModelStubs.getAudit(4L)
        ).collect(Collectors.toList());
        when(auditRepository.findAll()).thenReturn(repoTestStub);
        List<AuditDto> actual = auditService.getAll();
        assertNotNull(actual, "Expected not null");
        assertEquals(repoTestStub.size(), actual.size());
        for (int i = 0; i < repoTestStub.size(); i++) {
            assertEquals(repoTestStub.get(0).getId(), actual.get(0).getId());
        }
    }


    @Test
    void getById() {
        Audit expect = ModelStubs.getAudit(1L);
        when(auditRepository.getOne(1L)).thenReturn(expect);
        AuditDto actual = auditService.getById(1L);
        assertNotNull(actual, "Expected not null");
        assertEquals(expect.getId(), actual.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    private void saveOrUpdate(){
        when(auditRepository.save(any(Audit.class)))
                .thenReturn(ModelStubs.getAudit(1L));

        AuditDto actual = auditService
                .create(ModelStubs.getAuditDto(1L));

        assertEquals(1L, actual.getId());
        verify(auditRepository).save(any(Audit.class));
    }
}