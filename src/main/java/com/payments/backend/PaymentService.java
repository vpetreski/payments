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
    private final RedisTemplate<String, Object> redisTemplate;

    public Payment createPayment(Payment newPayment) {
        Payment existingPayment = paymentRepository.findByKey(newPayment.getKey());
        if (existingPayment != null) {
            log.info("Will NOT create Payment, because it already exists {}", existingPayment);
            return existingPayment;
        }

        log.info("Creating Payment {}", newPayment);
        newPayment.setStatus(Status.CREATED);
        Payment createdPayment = paymentRepository.save(newPayment);
        redisTemplate.opsForValue().set(String.valueOf(createdPayment.getId()), createdPayment);
        return createdPayment;
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
            .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
