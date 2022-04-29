package com.payments.backend;

import com.google.gson.Gson;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PaymentsClient {

    public static void main(String[] args) {
        final MediaType jsonMediaType = MediaType.get("application/json; charset=utf-8");
        final Gson gson = new Gson();
        final OkHttpClient client = new OkHttpClient();

        System.out.println("Client invoking Payment API...\n");

        for (int i = 0; i < 10; i++) {
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
            paymentRequest.setStatus(Status.CREATED);

            Request request = new Request.Builder()
                .url("http://localhost:8080/payments")
                .post(RequestBody.create(gson.toJson(paymentRequest), jsonMediaType))
                .build();
            try (Response response = client.newCall(request).execute()) {
                System.out.println(gson.fromJson(response.body().string(), Payment.class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("\nClient completed invoking Payment API.");
    }
}
