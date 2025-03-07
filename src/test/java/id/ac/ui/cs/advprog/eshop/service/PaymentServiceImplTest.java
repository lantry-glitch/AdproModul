package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;

    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Castorice");
        product1.setProductQuantity(7);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudarajat");
        orders.add(order2);

        payments = new ArrayList<>();
        Payment payment1 = new Payment(order1.getId(), "VOUCHER", new HashMap<>());
        payments.add(payment1);
        Payment payment2 = new Payment(order2.getId(), "VOUCHER", new HashMap<>());
        payments.add(payment2);
    }

    @Test
    void testCreatePayment(){
        Payment payment = payments.get(0);
        doReturn(null).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.addPayment(orders.get(0), "VOUCHER", new HashMap<>());
        verify(paymentRepository, times(1)).add(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testCreatePaymentIfAlreadyExists(){
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertNull(paymentService.addPayment(orders.get(0), "VOUCHER", new HashMap<>()));
        verify(paymentRepository, times(0)).add(payment);
    }

    @Test
    void testUpdateStatusInvalidStatus(){
        Payment payment = payments.get(1);

        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "CASTORICE"));
        verify(paymentRepository, times(0)).update(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidId(){
        Payment payment = new Payment("Tribios", "VOUCHER", new HashMap<>());

        assertThrows(NoSuchElementException.class, () -> paymentService.setStatus(payment, OrderStatus.SUCCESS.getValue()));
        verify(paymentRepository, times(0)).update(any(Payment.class));
    }

    @Test
    void testFindByIdIfIdFound(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testFindAllIfEmpty(){
        payments.clear();
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();
        assertTrue(results.isEmpty());
    }

    @Test
    void testFindAllIfNotEmpty(){
        doReturn(payments).when(paymentRepository).findAll();
        List<Payment> results = paymentService.getAllPayments();
        assertEquals(2, results.size());
    }
}