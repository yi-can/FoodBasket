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
public class AuthMailModel implements Serializable {
    private	String	activationCode;
    private	String	token;
    private	String	email;
    private String firstname;
}
