package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment add(Payment payment) {
        return null;
    }

    public Payment update(Payment payment) {
        return null;
    }

    public Payment findById(String id) {
        return null;
    }

    public List<Payment> findAll() {
        return null;
    }
}