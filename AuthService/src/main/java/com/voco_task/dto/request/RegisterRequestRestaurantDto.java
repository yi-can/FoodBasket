package com.voco_task.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestRestaurantDto {
    private String firstname;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private String profilePicture;
    private String gender;
    private String name;
    private String avatar;
    private List<String> phone;
    private List<String> address;
}
