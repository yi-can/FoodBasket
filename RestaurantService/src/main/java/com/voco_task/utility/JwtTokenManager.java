package com.voco_task.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rabbitmq.client.impl.OAuthTokenManagementException;
import com.voco_task.exceptions.ErrorType;
import com.voco_task.repository.enums.ERole;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class JwtTokenManager {

    String secretKey = "secretKey";
    String issuer = "issuer";

    public Optional<String> createToken(Long id){
        String token=null;
        Date date=new Date(System.currentTimeMillis()+(1000*60*5));
        try {
            token= JWT.create()
                    .withIssuer(issuer)
                    .withClaim("authId",id)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC512(secretKey));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  Optional.ofNullable(token);
    }

    public Optional<String> createToken(String id, ERole role){
        String token=null;
        Date date=new Date(System.currentTimeMillis()+(1000*60*5));
        try {
            token= JWT.create()
                    .withIssuer(issuer)
                    .withClaim("authId",id)
                    .withClaim("role",role.toString())
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC512(secretKey));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  Optional.ofNullable(token);
    }


}
