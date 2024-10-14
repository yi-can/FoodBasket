package com.voco_task.controller;


import com.voco_task.dto.request.UserUpdateDto;
import com.voco_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/update/user")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserUpdateDto dto, @RequestParam String token){
        return ResponseEntity.ok(userService.registerUser(dto, token));
    }

    @PostMapping("/delete/user")
    public ResponseEntity<String> deleteUser(@RequestParam String token){
        return ResponseEntity.ok(userService.deleteUser(token));
    }

}