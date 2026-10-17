package com.example.PaymentServiceJune2025.services;

import com.example.PaymentServiceJune2025.paymentgateways.IPaymentGateway;
import com.example.PaymentServiceJune2025.paymentgateways.PaymentGatewayChooserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    public String initiatePayment(Long amount, String orderId, String phoneNumber, String name, String email) {
        IPaymentGateway paymentGateway = paymentGatewayChooserStrategy.getBestPaymentGateway();
        return paymentGateway.generatePaymentLink(amount, orderId, phoneNumber, name, email);
    }
}
