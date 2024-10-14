package com.voco_task.controller;


import com.voco_task.dto.request.LoginRequestDto;
import com.voco_task.dto.request.PasswordForgotDto;
import com.voco_task.dto.request.RegisterRequestRestaurantDto;
import com.voco_task.dto.request.RegisterRequestUserDto;
import com.voco_task.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/user")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequestUserDto dto){
        return ResponseEntity.ok(authService.registerUser(dto));
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestRestaurantDto dto){
        return ResponseEntity.ok(authService.registerRestaurant(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @GetMapping("/activate/status")
    public ResponseEntity<String> activateStatus(@RequestParam String token){
        return ResponseEntity.ok(authService.activateStatus(token));
    }

    @PostMapping("/password/forgot")
    public ResponseEntity<String> forgot(@RequestBody PasswordForgotDto dto){
        return ResponseEntity.ok(authService.forgot(dto));
    }

}
