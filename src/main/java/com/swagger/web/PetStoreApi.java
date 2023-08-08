package com.swagger.web;

import com.swagger.model.Pet;
import com.swagger.model.Pet.Category;
import com.swagger.model.Pet.StatusName;
import com.swagger.model.Pet.Tagg;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
@Tag(
    name = "pet",
    description = "Everything about your Pets",
    externalDocs = @ExternalDocumentation(url = "http://swagger.io", description = "Find out more")
)
public class PetStoreApi {

    Pet pet = Pet
        .builder()
        .id(10L)
        .name("doggie")
        .category(Category.builder().id(2L).name("dog").status(StatusName.PENDING).build())
        .photoUrls(
            new ArrayList<String>() {
                {
                    add("http://url1.com");
                    add("http://url2.com");
                    add("http://url3.com");
                }
            }
        )
        .tags(
            new ArrayList<>() {
                {
                    add(Tagg.builder().id(1L).name("tag1").build());
                    add(Tagg.builder().id(2L).name("tag2").build());
                    add(Tagg.builder().id(3L).name("tag3").build());
                }
            }
        )
        .mapperCats(new String[] { "cat 1", "cat 2" })
        .status(StatusName.SOLD)
        .build();

    List<Pet> pets = new ArrayList<>() {
        {
            add(pet);
            add(pet);
        }
    };

    //#region updatePet
    @Operation(
        summary = "Update an existing pet",
        description = "Update an existing pet by Id",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Update an existent pet in the store", //
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pet.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = Pet.class)),
                @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE, schema = @Schema(implementation = Pet.class)),
            }
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pet.class)),
                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = Pet.class)),
                }
            ),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
            @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(schema = @Schema(hidden = true))),
        },
        tags = { "pet" },
        security = @SecurityRequirement(name = "Apikey")
    )
    @PutMapping
    // (
    //     // * consumes -> swagger tự tạo ra   @Operation( requestBody => content tương ứng mỗi mỗi  consumes)
    //     // * produces -> swagger tự tạo ra   @Operation( responses => @ApiResponse => content tương ứng mỗi mỗi  produces)
    //     // * nhìn ở trên là rõ
    //     // * nên sài cách dưới này , không nên cấu hình content trong @Operation mà nên dùng consumes và produces để auto tạo ra content
    //     consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE },
    //     produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    // )
    public ResponseEntity<Pet> updatePet(@RequestBody Pet body) {
        return new ResponseEntity<Pet>(pet, HttpStatus.NOT_IMPLEMENTED);
    }

    //#endregion

    //#region findPetsByStatus
    @Operation(
        summary = "Finds Pets by status",
        description = "Multiple status values can be provided with comma separated strings",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "successful operation",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Pet.class)))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid status value", content = @Content),
            @ApiResponse,
        }
    )
    @GetMapping(value = "/findByStatus", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Pet>> findPetsByStatus(
        // @Schema(
        //     description = "Status values that need to be considered for filter",
        //     allowableValues = { "available", "pending", "sold" },
        //     defaultValue = "sold"
        // )
        // @ArraySchema(
        //     schema = @Schema(
        //         description = "Status values that need to be considered for filter",
        //         allowableValues = { "available", "pending", "sold" }, //
        //         defaultValue = "sold"
        //     )
        // )
        @Parameter(
            description = "Status values that need to be considered for filter",
            schema = @Schema(allowableValues = { "available", "pending", "sold" }, defaultValue = "sold")
        ) @RequestParam(required = false) String status,
        @Parameter(
            explode = Explode.FALSE, // FALSE => tags=available,pending | true => tags=available&tags=pending
            description = "Status values that need to be considered for filter",
            array = @ArraySchema(schema = @Schema(allowableValues = { "available", "pending", "sold" }, defaultValue = "sold"))
        ) @RequestParam(required = false) String npm
    ) {
        return new ResponseEntity<List<Pet>>(pets, HttpStatus.NOT_IMPLEMENTED);
    }

    //#endregion

    //#region findPetsByTags
    @Operation(
        summary = "Finds Pets by tags",
        description = "Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid tag value", content = @Content),
        }
    )
    @GetMapping(value = "/findByTags", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Pet>> findPetsByTags(
        @Parameter(
            explode = Explode.FALSE, // tags=available,pending | true => tags=available&tags=pending
            description = "Tags to filter by",
            array = @ArraySchema(schema = @Schema(allowableValues = { "available", "pending", "sold" }))
        ) @RequestParam(required = false) List<String> tags
    ) {
        return new ResponseEntity<List<Pet>>(pets, HttpStatus.NOT_IMPLEMENTED);
    }

    //#endregion

    //#region getPetById
    @Operation(
        summary = "Find pet by ID",
        description = "Returns a single pet",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        }
    )
    @GetMapping(value = "/{petId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Pet> getPetById(@Parameter(description = "ID of pet to return") @PathVariable Long petId) {
        return new ResponseEntity<Pet>(pet, HttpStatus.NOT_IMPLEMENTED);
    }

    //#endregion

    //#region updatePetWithForm
    @Operation(
        summary = "Updates a pet in the store with form data",
        description = "",
        responses = @ApiResponse(responseCode = "405", description = "Invalid input")
    )
    @PostMapping("/{petId}")
    public ResponseEntity<Void> updatePetWithForm(
        @Parameter(description = "ID of pet that needs to be updated") @PathVariable("petId") Long petId,
        @Parameter(description = "Name of pet that needs to be updated") @RequestParam(value = "name", required = false) String name,
        @Parameter(description = "Status of pet that needs to be updated") @RequestParam(value = "status", required = false) String status
    ) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    //#endregion

    //#region deletePet
    @Operation(
        summary = "Deletes a pet",
        description = "delete a pet",
        responses = { @ApiResponse(responseCode = "400", description = "Invalid pet value") }
    )
    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(
        @RequestHeader(value = "api_key", required = false) String apiKey,
        @Parameter(description = "Pet id to delete") @PathVariable Long petId
    ) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    //#endregion

    //#region uploadFile
    @Operation(
        summary = "uploads an image",
        description = "",
        responses = { @ApiResponse(responseCode = "200", description = "successful operation") }
    )
    @PostMapping(value = "/{petId}/uploadImage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
        @Parameter(description = "ID of pet to update") @PathVariable Long petId,
        @Parameter(description = "Additional Metadata") @RequestParam(required = false) String additionalMetadata,
        @Parameter(description = "file upload") @RequestPart(required = true) MultipartFile file
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    //#endregion

    //#region loginUser
    @Operation(
        summary = "Logs user into the system",
        description = "",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "successful operation",
                headers = {
                    @Header(
                        name = "X-Rate-Limit",
                        description = "calls per hour allowed by the user",
                        schema = @Schema(type = "integer", format = "int32")
                    ),
                    @Header(
                        name = "X-Expires-After",
                        description = " date in UTC when token expires",
                        schema = @Schema(type = "string", format = "date-time")
                    ),
                }
            ),
            @ApiResponse(responseCode = "400", description = "Invalid username/password supplied", content = @Content),
        }
    )
    @PostMapping(value = "/user/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<String> loginUser(
        @Parameter(description = "The user name for login") @RequestParam(required = false) String username,
        @Parameter(description = "The password for login in clear text") @RequestParam(required = false) String password
    ) {
        return new ResponseEntity<String>("ok", HttpStatus.NOT_IMPLEMENTED);
    }
    //#endregion

}
