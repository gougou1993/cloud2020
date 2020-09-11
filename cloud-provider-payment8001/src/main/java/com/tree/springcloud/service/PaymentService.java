package com.tree.springcloud.service;

import com.tree.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
