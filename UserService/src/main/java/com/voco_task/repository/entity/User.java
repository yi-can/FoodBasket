package com.voco_task.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User extends BaseEntity{
    @Id
    private	String	userId;
    private	String	firstname;
    private	String	surname;
    private	String	phoneNumber;
    private	String	email;
    private	String	password;
    private	String	profilePicture;
    private	String	gender;
    private String address;
    private	List<String> creditCardId;

}
