package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData);
        boolean paymentExists = paymentRepository.findById(payment.getId()) != null;
        if (!paymentExists) {
            paymentRepository.add(payment);
            return payment;
        }
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        boolean paymentExists = paymentRepository.findById(payment.getId()) != null;
        if (!paymentExists) {
            throw new NoSuchElementException();
        }
        paymentRepository.update(payment);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}