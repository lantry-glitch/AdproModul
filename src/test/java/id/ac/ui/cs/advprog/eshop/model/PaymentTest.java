package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.InvalidArgumentException;

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
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentEmptyData() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(order.getId(),
                PaymentMethod.VOUCHER.getValue(), null));
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment(order.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment(order.getId(), PaymentMethod.VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(order.getId(),
                PaymentMethod.VOUCHER.getValue(), "CANCELLED", paymentData));
    }

    @Test
    void testSetPaymentStatusSuccess() {
        Payment payment = new Payment(order.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentStatusInvalid() {
        Payment payment = new Payment(order.getId(), PaymentMethod.VOUCHER.getValue(),"MEOW", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("CANCELLED"));
    }
    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(order.getId(),
                "FIREFLY", paymentData));
    }

    @Test
    void testCreateVoucherPaymentEmptyData() {
        paymentData.clear();

        Payment payment = new Payment(order.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentNot16Characters() {
        paymentData.clear();
        paymentData.put("voucherCode", "ESHOPFIREFLY12345678");

        Payment payment = new Payment(order.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentNot8Numbers() {
        paymentData.clear();
        paymentData.put("voucherCode", "ESHOP1234ABCDE78");

        Payment payment = new Payment(order.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentNotStartWithEshop() {
        paymentData.clear();
        paymentData.put("voucherCode", "DSHIP1234ABC5678");

        Payment payment = new Payment(order.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
    @Test
    void testCreateBankTransferPaymentValid() {
        paymentData.clear();
        paymentData.put("bankName", "BRI");
        paymentData.put("referenceCode", "A12345678XXX");

        Payment payment = new Payment(order.getId(), PaymentMethod.BANKTRANSFER.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentNoBankName() {
        paymentData.clear();
        paymentData.put("referenceCode", "A12345678XXX");

        Payment payment = new Payment(order.getId(), PaymentMethod.BANKTRANSFER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentNoReferenceCode() {
        paymentData.clear();
        paymentData.put("bankName", "BRI");

        Payment payment = new Payment(order.getId(), PaymentMethod.BANKTRANSFER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
