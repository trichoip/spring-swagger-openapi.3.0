package com.swagger.web;

import com.swagger.Constants.SwaggerTagConstants;
import com.swagger.model.People;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/github")
// áp dụng cho toàn bộ method trong class này
@Tag(name = SwaggerTagConstants.GITHUB_API)
// @SecurityRequirements
// @Deprecated
// @Hidden
@ApiResponses(
    {
        @ApiResponse(responseCode = "201", description = "description 201", content = @Content), //
        @ApiResponse(responseCode = "202", description = "description 202"),
    }
)
@ApiResponse(
    responseCode = "200",
    description = "description 200",
    headers = {
        @Header(name = HttpHeaders.LOCATION, description = "URL of the entity created", schema = @Schema(type = "interger", example = "example")),
        @Header(name = HttpHeaders.AUTHORIZATION, description = "UUID generated entity", schema = @Schema(type = "string", example = "example")),
    },
    content = {
        @Content(
            mediaType = MediaType.ALL_VALUE,
            schema = @Schema(implementation = People.class),
            examples = {
                @ExampleObject(name = "default", description = "default json", summary = "default json summary"),
                @ExampleObject(name = "name 1", description = "not summary , have name", value = "value 1"),
                @ExampleObject(name = "name 2", description = "have summary , not name", summary = "summary 2", value = "value 2"),
            }
        ),
        @Content(
            mediaType = "application/json",
            schema = @Schema(
                allOf = People.class,
                description = "post Syllabus",
                externalDocs = @ExternalDocumentation(url = "https://github.com/"),
                title = "Syllabus",
                defaultValue = "yes yes"
            )
        ),
    }
)
public class GithubApi {

    @PostMapping("/swagger")
    // @Deprecated
    // @Hidden
    // @Tag(name = "gg")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "401", description = "Unauthorized, missing or invalid JWT", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied, do not have permission to access this resource", content = @Content),
        }
    )
    @ApiResponse(responseCode = "405", description = "Unauthorized, missing or invalid JWT", content = @Content)
    @Operation(
        operationId = "opennode",
        summary = "this is summary",
        description = "this is description",
        externalDocs = @ExternalDocumentation(url = "https://github.com", description = "this is externalDocs", extensions = {}),
        // deprecated = true,
        // hidden = true,
        tags = { "gg", "gg1" },
        responses = {
            @ApiResponse(responseCode = "501", description = "description 501", content = @Content), //
            @ApiResponse(responseCode = "504", description = "description 504", content = @Content),
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        allOf = People.class,
                        description = "put Syllabus",
                        externalDocs = @ExternalDocumentation(url = "https://github.com/"),
                        title = "Syllabus",
                        defaultValue = "yes yes"
                    )
                ),
                @Content(
                    mediaType = "application/schema+json",
                    schema = @Schema(implementation = People.class),
                    examples = {
                        @ExampleObject(name = "default", description = "default json", summary = "default json"),
                        @ExampleObject(name = "name 1", description = "not summary , have name", value = "value 1"),
                        @ExampleObject(name = "name 2", description = "have summary , not name", summary = "summary 2", value = "value 2"),
                    }
                ),
                @Content(mediaType = "application/vnd.api+json", schema = @Schema(implementation = People.class)),
                @Content(mediaType = "application/vnd.geo+json", schema = @Schema(implementation = People.class)),
            },
            description = "hello hello"
        ),
        servers = @Server(
            url = "{URL}",
            description = "Server config url",
            variables = @ServerVariable(
                name = "URL",
                description = "this is url",
                allowableValues = {
                    "https://json.schemastore.org", //
                    "https://api.github.com",
                    "https://etransportation-webapp-api.azurewebsites.net",
                    "http://localhost:8080",
                },
                defaultValue = "http://localhost:8080"
            )
        ),
        security = {
            @SecurityRequirement(name = "Apikey"), //
            @SecurityRequirement(name = "Apikey2"),
        },
        extensions = @Extension(
            name = "dog",
            properties = {
                @ExtensionProperty(name = "name", value = "hot dog"), //
                @ExtensionProperty(name = "age", value = "15", parseValue = true), // parseValue = true =>  "nonparsedvalue": 12345  | false => "nonparsedvalue": "12345"
            }
        )
    )
    public ResponseEntity<People> swagger(@Valid @RequestBody People people) {
        return ResponseEntity.ok(people);
    }

    @GetMapping("/swagger2")
    public ResponseEntity<?> swagger2(
        @ArraySchema(maxItems = 2, minItems = 1, uniqueItems = true) @RequestParam(required = false) String[] scanning3,
        @ArraySchema(
            schema = @Schema(allowableValues = { "allow", "replace", "skip" }),
            maxItems = 2,
            minItems = 1,
            uniqueItems = true
        ) @RequestParam(required = false) String[] scanning2,
        @Schema(description = "description", type = "array") @RequestParam(required = false) String[] scanning,
        @ArraySchema(
            schema = @Schema(allowableValues = { "allow", "allow", "replace", "skip" }),
            maxItems = 2,
            minItems = 1,
            uniqueItems = true
        ) @RequestParam(required = true) String handle,
        @Schema(
            allowableValues = { "allow", "replace", "skip" },
            defaultValue = "replace",
            description = "description ne",
            deprecated = true,
            example = "skip"
        ) @RequestParam(required = true) @Parameter(
            in = ParameterIn.HEADER,
            description = "description",
            allowEmptyValue = true,
            extensions = @Extension(
                name = "dog",
                properties = {
                    @ExtensionProperty(name = "name", value = "hot dog"), //
                    @ExtensionProperty(name = "age", value = "15", parseValue = true), // parseValue = true =>  "nonparsedvalue": 12345  | false => "nonparsedvalue": "12345"
                }
            )
        ) String handle2,
        @RequestHeader @Schema(
            allowableValues = { "allow", "replace", "skip" },
            defaultValue = "replace",
            description = "description ne",
            deprecated = true,
            example = "skip"
        ) String header,
        @ParameterObject People people
    ) {
        return ResponseEntity.ok(handle);
    }

    @GetMapping("/ResponseStatus")
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public void exampleApi() {
        // Phương thức này không cần trả về giá trị cụ thể, chỉ trả về status code 202 Accepted.
    }

    @GetMapping("/file")
    public void file(@RequestPart(value = "file", required = true) MultipartFile file) {
        // Phương thức này không cần trả về giá trị cụ thể, chỉ trả về status code 202 Accepted.
    }

    @GetMapping("/users")
    public ResponseEntity<?> users() {
        return ResponseEntity.ok("users");
    }

    @GetMapping("/emojis")
    public ResponseEntity<?> emojis(@RequestHeader(name = "Authorization") String name) {
        return ResponseEntity.ok("emojis");
    }

    @GetMapping("/feeds")
    public ResponseEntity<?> feeds() {
        return ResponseEntity.ok("feeds");
    }

    @GetMapping("/events")
    public ResponseEntity<?> events() {
        return ResponseEntity.ok("events");
    }

    @GetMapping("/")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok("all");
    }
}
