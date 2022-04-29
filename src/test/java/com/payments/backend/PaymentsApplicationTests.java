package com.payments.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PaymentsApplicationTests {

    @Autowired
    private PaymentService paymentService;

    @Test
    void contextLoads() {
        assertThat(paymentService).isNotNull();
    }

    @Test
    void testPayments() {
        Payment paymentRequest = new Payment();
        paymentRequest.setKey(UUID.randomUUID().toString());
        paymentRequest.setCurrency("USD");
        paymentRequest.setAmount(new BigDecimal("256.87"));
        paymentRequest.setOriginatorId("567");
        paymentRequest.setOriginatorName("Y");
        paymentRequest.setBeneficiaryId("123");
        paymentRequest.setBeneficiaryName("X");
        paymentRequest.setSenderAccountType("CHECKING");
        paymentRequest.setSenderAccountNumber("765543322");
        paymentRequest.setReceiverAccountType("CHECKING");
        paymentRequest.setReceiverAccountNumber("4932867423");

        Payment createdPayment = paymentService.createPayment(paymentRequest);

        assertThat(createdPayment.getId()).isNotNull();
        assertThat(createdPayment.getStatus()).isEqualByComparingTo(Status.CREATED);

        assertThat(paymentService.getPayment(createdPayment.getId()).getId()).isEqualTo(createdPayment.getId());
    }
}
