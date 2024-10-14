package com.voco_task.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthUserModel implements Serializable {
    private String firstname;
    private String surname;
    private String phoneNumber;
    private String password;
    private String email;
    private String profilePicture;
    private String gender;
    private String address;

}
