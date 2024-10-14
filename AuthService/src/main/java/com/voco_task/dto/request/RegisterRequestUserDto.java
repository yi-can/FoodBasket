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
public class RegisterRequestUserDto {
    private String firstname;
    private String surname;
    private String phoneNumber;
    private String password;
    private String email;
    private String profilePicture;
    private String gender;
    private String address;
}
