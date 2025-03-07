package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.status = PaymentStatus.REJECTED.getValue();

        if (paymentData == null) {
            throw new IllegalArgumentException();
        }
        else {
            this.paymentData = paymentData;
        }
    }

    Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.setStatus(status);

        if (paymentData == null) {
            throw new IllegalArgumentException();
        }
        else {
            this.paymentData = paymentData;
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}