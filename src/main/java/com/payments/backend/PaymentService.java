package com.payments.backend;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RedisTemplate<String, Payment> redisTemplate;

    public Payment createPayment(Payment newPayment) {
        Payment queuePayment = redisTemplate.opsForValue().get(newPayment.getKey());
        Payment dbPayment = paymentRepository.findByKey(newPayment.getKey());

        if (queuePayment != null || dbPayment != null) {
            log.info("Will NOT create the Payment, because it already exists!");
            return dbPayment != null ? dbPayment : queuePayment;
        }

        log.info("Creating Payment {}", newPayment);
        newPayment.setStatus(Payment.Status.CREATED);
        redisTemplate.opsForValue().set(String.valueOf(newPayment.getKey()), newPayment);
        return paymentRepository.save(newPayment);
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
            .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
