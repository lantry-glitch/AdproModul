package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void testApplicationContextLoads() {
        assertDoesNotThrow(() -> id.ac.ui.cs.advprog.eshop.EshopApplication.main(new String[]{}));
    }

}