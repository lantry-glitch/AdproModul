package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudarajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee607ecbf1e", products,
                1708570000L, "Bambang Sudrajat");
        orders.add(order3);

        payments = new ArrayList<>();
        Map<String, String> paymentData = new HashMap<>();
        Payment payment1 = new Payment(order1.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        payments.add(payment1);
        Payment payment2 = new Payment(order2.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        payments.add(payment2);
        Payment payment3 = new Payment(order3.getId(), PaymentMethod.VOUCHER.getValue(), paymentData);
        payments.add(payment3);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.get(0);
        Payment result = paymentRepository.add(payment);

        Payment findResult = paymentRepository.findById(orders.get(0).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate(){
        Payment payment = payments.get(0);
        paymentRepository.add(payment);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(),
                PaymentStatus.REJECTED.getValue(), payment.getPaymentData());
        Payment result = paymentRepository.update(newPayment);

        Payment findResult = paymentRepository.findById(orders.get(0).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment : payments){
            paymentRepository.add(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        for (Payment payment : payments){
            paymentRepository.add(payment);
        }
        Payment findResult = paymentRepository.findById("kkkk");
        assertNull(findResult);
    }

    @Test
    void testFindAllWhileEmpty(){
        List<Payment> paymentList = paymentRepository.findAll();
        assertTrue(paymentList.isEmpty());
    }

    @Test
    void testFindAllWhileFilled(){
        for (Payment payment : payments){
            paymentRepository.add(payment);
        }
        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(3, paymentList.size());
    }
}