package com.payments.backend;

class PaymentNotFoundException extends RuntimeException {

    PaymentNotFoundException(Long id) {
        super("Could not find payment " + id);
    }
}