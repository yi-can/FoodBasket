package com.voco_task.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CreditCard extends BaseEntity{
    @Id
    private	String	creditCardId;
    private	String	name_surname;
    private	String	cardNumber;
    private	String	cardExpiryDate;
    private	String	securityCode;
}
