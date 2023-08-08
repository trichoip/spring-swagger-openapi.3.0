package com.swagger.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Tag 1") // nhóm các method trong class này vào tag 1
@Tags(
    {
        // muốn description tag thì phải dùng @Tag mới description được
        // còn tags trong Operation thì không
        @Tag(name = "Tag 2", description = "REST API endpoints for Tag 2"), //
        @Tag(name = "Tag 3"),
    }
) // nhóm các method trong class này vào tag 1 , authentication, tag 2
public class SwaggerTag {

    @GetMapping("/tag1")
    @Tag(name = "Tag 4")
    @Operation(tags = { "Tag 5", "Tag 6" }) // @Operation tags chỉ định method này vào tag
    // tag trong Operation khong description được mà chỉ bỏ method vào tag
    public ResponseEntity<?> tag1() {
        return ResponseEntity.ok("tag 1");
    }
}
