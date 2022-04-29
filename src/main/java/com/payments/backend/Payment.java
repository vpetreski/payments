package com.payments.backend;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Payment implements Serializable {

    public enum Status {
        CREATED, SENT, ACCEPTED, REJECTED;
    }

    private @Id
    @GeneratedValue Long id;
    @NotBlank(message = "Key is mandatory")
    private String key;
    @NotBlank(message = "Currency is mandatory")
    @Size(message = "Currency has only 3 characters", min = 3, max = 3)
    private String currency;
    @NotNull(message = "Amount is mandatory")
    @Digits(message = "Amount must be a number", integer = 100, fraction = 100)
    private BigDecimal amount;
    @NotBlank(message = "Originator name is mandatory")
    private String originatorName;
    @NotBlank(message = "Originator ID is mandatory")
    private String originatorId;
    @NotBlank(message = "Beneficiary name is mandatory")
    private String beneficiaryName;
    @NotBlank(message = "Beneficiary ID is mandatory")
    private String beneficiaryId;
    @NotBlank(message = "Sender account type is mandatory")
    private String senderAccountType;
    @NotBlank(message = "Sender account number is mandatory")
    private String senderAccountNumber;
    @NotBlank(message = "Receiver account type is mandatory")
    private String receiverAccountType;
    @NotBlank(message = "Receiver account number is mandatory")
    private String receiverAccountNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
}
