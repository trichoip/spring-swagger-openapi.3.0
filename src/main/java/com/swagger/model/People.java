package com.swagger.model;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "UserPeppe")
public class People {

    @Max(1000)
    @Min(1)
    @Schema(
        extensions = @Extension(
            name = "dog",
            properties = {
                @ExtensionProperty(name = "name", value = "hot dog"), //
                @ExtensionProperty(name = "age", value = "15", parseValue = true), // parseValue = true =>  "nonparsedvalue": 12345  | false => "nonparsedvalue": "12345"
            }
        ),
        description = "User ID",
        example = "1",
        title = "id people",
        externalDocs = @ExternalDocumentation(url = "https://github.com", description = "this is externalDocs", extensions = {}),
        maximum = "1000", // @Max(1000)
        minimum = "1", // @Min(1)
        maxLength = 1, // @Size(max = 100, min = 10)
        minLength = 1, // @Size(max = 100, min = 10)
        pattern = "\\d" // @Pattern(regexp = "\\d")
        // hidden = true,
        // deprecated = true,
        // AccessMode.READ_ONLY sẽ hiển thị id nếu get còn post, put thì swagger giúp ẩn đi id trên swagger ui
        // WRITE_ONLY sẽ hiển thị id nếu post, put còn get thì ẩn
        // accessMode = AccessMode.WRITE_ONLY,
        // writeOnly = true, // AccessMode.WRITE_ONLY
        // readOnly = true, // AccessMode.READ_ONLY
    )
    private Long id;

    @Size(max = 2, min = 1)
    @ArraySchema(
        schema = @Schema(description = "List of user roles aray", allowableValues = { "dog", "cat" }),
        maxItems = 2,
        minItems = 1,
        uniqueItems = true
    )
    @NotNull
    private String[] roles;
}
