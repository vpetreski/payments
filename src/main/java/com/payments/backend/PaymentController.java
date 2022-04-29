package com.payments.backend;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "payment", description = "The Payment resource")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payments")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    Payment newPayment(@Valid @RequestBody Payment newPayment) {
        return paymentService.createPayment(newPayment);
    }

    @GetMapping("/payments/{id}")
    Payment one(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    @GetMapping("/payments")
    List<Payment> all() {
        return paymentService.getAllPayments();
    }
}
