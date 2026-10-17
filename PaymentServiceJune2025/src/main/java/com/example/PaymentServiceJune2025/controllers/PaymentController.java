package com.example.PaymentServiceJune2025.controllers;

import com.example.PaymentServiceJune2025.dtos.InitiatePaymentRequestDto;
import com.example.PaymentServiceJune2025.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto) {
        return paymentService.initiatePayment(initiatePaymentRequestDto.getAmount(), initiatePaymentRequestDto.getOrderId(), initiatePaymentRequestDto.getPhoneNumber(), initiatePaymentRequestDto.getName() ,initiatePaymentRequestDto.getEmail());

    }

}
