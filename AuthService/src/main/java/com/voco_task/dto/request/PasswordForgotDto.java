package com.voco_task.dto.request;

import com.voco_task.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForgotDto {
    private	String	email;
    private	String	activationCode;
}
