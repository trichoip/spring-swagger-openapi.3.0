package com.swagger.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Tag(name = "Security Requirement")
// @SecurityRequirement(name = "Apikey2")
// @SecurityRequirements
public class SwaggerSecurityRequirement {

    @GetMapping("/Golbal-Bearer")
    public ResponseEntity<?> security1() {
        return ResponseEntity.ok("Golbal-Bearer");
    }

    @GetMapping("/Non-Security-Golbal")
    @SecurityRequirements
    public ResponseEntity<?> security2() {
        return ResponseEntity.ok("Non-Security-Golbal");
    }

    @GetMapping("/Apikey-config-SecurityRequirement")
    @SecurityRequirement(name = "Apikey")
    public ResponseEntity<?> security3() {
        return ResponseEntity.ok("Apikey-config-SecurityRequirement");
    }

    @GetMapping("/Apikey2-Apikey-config-SecurityRequirements")
    @SecurityRequirements(
        {
            @SecurityRequirement(name = "Apikey"), //
            @SecurityRequirement(name = "Apikey2"),
        }
    )
    public ResponseEntity<?> security4() {
        return ResponseEntity.ok("Apikey2-Apikey-config-SecurityRequirements");
    }
}
