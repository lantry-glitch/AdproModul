package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Product> products;
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("prod-1001");
        product1.setProductName("Baju Baru Bu Dedi");
        product1.setProductQuantity(3);

        Product product2 = new Product();
        product2.setProductId("prod-1002");
        product2.setProductName("Celana abal-abal");
        product2.setProductQuantity(5);

        this.products.add(product1);
        this.products.add(product2);

        order = new Order("order-2025", this.products, 1710000000L, "Alice");

        paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentEmptyData() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(order.getId(),
                "VOUCHER", null));
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment(order.getId(), "VOUCHER", paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment(order.getId(), "VOUCHER", PaymentStatus.SUCCESS.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(order.getId(),
                "VOUCHER", "CANCELLED", paymentData));
    }

    @Test
    void testSetPaymentStatusSuccess() {
        Payment payment = new Payment(order.getId(), "VOUCHER", paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentStatusInvalid() {
        Payment payment = new Payment(order.getId(), "VOUCHER", "MEOW", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("CANCELLED"));
    }
}
