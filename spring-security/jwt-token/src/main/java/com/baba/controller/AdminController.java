package com.baba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<String> getUser1() {
        return new ResponseEntity<>("Admin able to access Get method.", HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<String> getUser2() {
        return new ResponseEntity<>("Admin able to access Post method.", HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<String> getUser3() {
        return new ResponseEntity<>("Admin able to access Put method.", HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> getUser4() {
        return new ResponseEntity<>("Admin able to access Delete method.", HttpStatus.OK);
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('admin:patch')")
    public ResponseEntity<String> getUser5() {
        return new ResponseEntity<>("Admin able to access Patch method.", HttpStatus.OK);
    }

}
