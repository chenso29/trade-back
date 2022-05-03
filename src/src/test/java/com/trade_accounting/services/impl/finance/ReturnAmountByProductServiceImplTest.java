package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.repositories.finance.ReturnAmountByProductRepository;
import com.trade_accounting.utils.mapper.finance.ReturnAmountByProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ReturnAmountByProductServiceImplTest {
    @Mock
    ReturnAmountByProductRepository returnAmountByProductRepository;

    @Spy
    ReturnAmountByProductMapper returnAmountByProductMapper;

    @InjectMocks
    ReturnAmountByProductServiceImpl returnAmountByProductService;

    @Test
    void getRepoFilledNotNull() {
        assertNotNull(returnAmountByProductRepository);
    }
}
