package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
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

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method);
        this.status = PaymentStatus.REJECTED.getValue();

        if (paymentData == null) {
            throw new IllegalArgumentException();
        }
        else {
            this.setPaymentData(paymentData);
        }
    }

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method);
        this.setStatus(status);

        if (paymentData == null) {
            throw new IllegalArgumentException();
        }
        else {
            this.setPaymentData(paymentData);
        }
    }

    public void setStatus(String status) {
        if (PaymentMethod.contains(method)) {
            this.status = status;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public void setMethod(String method) {
        if (PaymentMethod.contains(method)) {
            this.method = method;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        this.paymentData = paymentData;
        if (this.method.equals(PaymentMethod.VOUCHER.getValue())) {
            if (!paymentData.containsKey("voucherCode")) {
                this.status = PaymentStatus.REJECTED.getValue();
            }
            else {
                String voucherCode = paymentData.get("voucherCode");
                if (!voucherCodeIsValid(voucherCode)) {
                    this.status = PaymentStatus.REJECTED.getValue();
                }
                else {
                    this.status = PaymentStatus.SUCCESS.getValue();
                }
            }
        }
        else if (this.method.equals(PaymentMethod.BANKTRANSFER.getValue())) {
            if (!paymentData.containsKey("bankName") || !paymentData.containsKey("referenceCode")) {
                this.status = PaymentStatus.REJECTED.getValue();
            }
            else {
                String bankName = paymentData.get("bankName");
                String referenceCode = paymentData.get("referenceCode");
                if (bankName.isEmpty() || referenceCode.isEmpty()) {
                    this.status = PaymentStatus.REJECTED.getValue();
                }
                else {
                    this.status = PaymentStatus.SUCCESS.getValue();
                }
            }
        }
    }

    private boolean voucherCodeIsValid(String voucherCode) {
        if (voucherCode.length() != 16) {
            return false;
        }
        else if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        int numCount = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i))) {
                numCount++;
            }
        }
        if (numCount != 8) {
            return false;
        }
        return true;
    }
}