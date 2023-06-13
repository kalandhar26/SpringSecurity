package com.baba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<String> getUser1() {
        return new ResponseEntity<>("User able to access Get method.", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> getUser2() {
        return new ResponseEntity<>("User able to access Post method.", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> getUser3() {
        return new ResponseEntity<>("User able to access Put method.", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> getUser4() {
        return new ResponseEntity<>("User able to access Delete method.", HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<String> getUser5() {
        return new ResponseEntity<>("User able to access Patch method.", HttpStatus.OK);
    }
}
