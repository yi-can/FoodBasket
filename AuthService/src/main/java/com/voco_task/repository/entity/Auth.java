package com.voco_task.repository.entity;

import com.voco_task.repository.enums.ERole;
import com.voco_task.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Auth extends BaseEntity{
    @Id
    private	String	authId;
    @Indexed(unique = true)
    private	String	email;
    private	String	password;
    private	ERole	role;
    private	Boolean	logged;
    private	String	activationCode;
    private EStatus status=EStatus.INACTIVE;
}
