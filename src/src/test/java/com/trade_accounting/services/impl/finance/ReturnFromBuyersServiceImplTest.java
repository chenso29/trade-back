package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.services.impl.finance.ReturnFromBuyersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReturnFromBuyersServiceImplTest {
    @Autowired
    ReturnFromBuyersServiceImpl returnFromBuyersService;


    @Test
    void getAll() {
        System.out.println(returnFromBuyersService.getAll());
        assertEquals(1, 1);
    }

    @Test
    void getById() {
        System.out.println(returnFromBuyersService.getById(3L));
        assertEquals(1, 1);
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
        returnFromBuyersService.deleteById(2L);
        assertEquals(1, 1);
    }

    @Test
    void search() {
    }
}