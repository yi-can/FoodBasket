package com.voco_task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreaditCardDto {
    private	String	name_surname;
    private	String	cardNumber;
    private	String	cardExpiryDate;
    private	String	securityCode;
}
