package com.swagger.config;

import com.swagger.Constants.SwaggerTagConstants;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

// * @OpenAPIDefinition và @SecurityScheme có thể cấu hình ở Application
// * nếu cấu hình ở ngoài Application thì phải có @Configuration
// * nếu không có thì khi thay đổi devtool reload thì nó sẽ không update thay đổi
// * nếu có @Configuration thì khi thay đổi thì devtool reload sẽ update thay đổi
// * nếu cấu hình ở Application thì không cần  @Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Swagger for System",
        description = "_This is list of endpoints and documentations of `REST API for System`_" +
        "\n - [https://editor.swagger.io/](https://editor.swagger.io/) \n" +
        "\n - [https://petstore3.swagger.io/](https://petstore3.swagger.io/?filter=pet) \n" +
        "\n - [https://swagger.io/specification/](https://swagger.io/specification/) \n" +
        "- [https://swagger.io/docs/open-source-tools/swagger-ui/usage/configuration](https://swagger.io/docs/open-source-tools/swagger-ui/usage/configuration)",
        version = "7.0",
        termsOfService = "policy", // giống với url
        contact = @Contact(email = "github@email.com", name = "Githubb", url = "https://github.com", extensions = {}),
        license = @License(name = "Github license", url = "policy", extensions = {}),
        extensions = {}
    ),
    externalDocs = @ExternalDocumentation(url = "https://github.com", description = "this is externalDocs", extensions = {}),
    servers = {
        @Server(url = "http://localhost:8080"),
        @Server(
            url = "{SCHEMA}://{HOST}{PORT}",
            description = "Server config",
            variables = {
                @ServerVariable(
                    // phải cùng tên ở url mới có hiệu lực => {HOST}
                    name = "HOST",
                    description = "this is host",
                    allowableValues = {
                        "json.schemastore.org", //
                        "api.github.com",
                        "etransportation-webapp-api.azurewebsites.net",
                        "localhost",
                    },
                    defaultValue = "localhost",
                    extensions = {}
                ),
                @ServerVariable(name = "PORT", description = "this is port", allowableValues = { "/", ":80", ":8080" }, defaultValue = ":8080"),
                @ServerVariable(name = "SCHEMA", description = "this is schema", allowableValues = { "http", "https" }, defaultValue = "http"),
                @ServerVariable(name = "Sample", description = "this is sample", allowableValues = { "" }, defaultValue = "https"),
            },
            extensions = {}
        ),
        @Server(
            url = "{URL}",
            description = "Server config url",
            variables = {
                @ServerVariable(
                    name = "URL",
                    description = "this is url",
                    allowableValues = {
                        "https://json.schemastore.org", //
                        "https://api.github.com",
                        "https://etransportation-webapp-api.azurewebsites.net",
                        "http://localhost:8080",
                    },
                    defaultValue = "https://api.github.com",
                    extensions = {}
                ),
            },
            extensions = {}
        ),
        @Server(url = "https://json.schemastore.org/launchsettings.json"),
        @Server(url = "https://api.github.com"),
    },
    tags = {
        @Tag(name = "Tag 1", description = "REST API endpoints for Tag 1"),
        @Tag(name = "Security Requirement", description = "REST API endpoints for Security Requirement"),
        @Tag(
            name = SwaggerTagConstants.COMPONENT_BEAN,
            description = "REST API endpoints for Component",
            externalDocs = @ExternalDocumentation(url = "https://api.github.com", description = "github io", extensions = {}),
            extensions = {}
        ),
        @Tag(
            name = SwaggerTagConstants.GITHUB_API,
            description = "REST API endpoints for Github Api",
            externalDocs = @ExternalDocumentation(url = "https://api.github.com", description = "github io", extensions = {}),
            extensions = {}
        ),
    },
    // extensions bổ sung Property vào openapi doc , không hiển thị trong swagger ui
    extensions = {
        @Extension(
            name = "dog",
            properties = {
                @ExtensionProperty(name = "name", value = "hot dog"), //
                @ExtensionProperty(name = "age", value = "15", parseValue = true), // parseValue = true =>  "nonparsedvalue": 12345  | false => "nonparsedvalue": "12345"
            }
        ),
        @Extension(
            name = "cat",
            properties = {
                @ExtensionProperty(name = "name", value = "cat meow"), //
                @ExtensionProperty(name = "age", value = "19", parseValue = true),
            }
        ),
        // có cấu hình name như ở trên thì nó sẽ nhóm tất cả propertie còn không có thì nó tách ra
        // vào doc openapi mà xem là rõ
        @Extension(
            properties = {
                @ExtensionProperty(name = "die", value = "true"), //
                @ExtensionProperty(name = "alive", value = "true", parseValue = true),
            }
        ),
    },
    // cấu hình security SecurityRequirement trong @OpenAPIDefinition thì nó áp dụng cho tất cả api
    // nếu api nào không cần Security thì dùng  @SecurityRequirements nó sẽ ghi đè (vào doc sẽ hiểu)
    // ví dụ, api a đang có 3 SecurityRequirement thì khi dùng @SecurityRequirements thì nó sẽ ghi đè thành 0 SecurityRequirement vì nếu dùng @SecurityRequirements mà không config thì nó trả về mảng 0
    // security = {
    //     @SecurityRequirement(name = "Bearer"), //
    //     @SecurityRequirement(name = "Apikey"),
    //     @SecurityRequirement(name = "Apikey2"),
    // }
    security = @SecurityRequirement(name = "Bearer")
)
@SecuritySchemes(
    {
        @SecurityScheme(
            name = "Apikey",
            description = "Enter the JWT token in the format '{Bearer token}'.",
            type = SecuritySchemeType.APIKEY,
            bearerFormat = "JWT", // mô tả trong doc openapi
            in = SecuritySchemeIn.HEADER, // require
            paramName = "Authorization-x" // require
        ),
        @SecurityScheme(
            name = "Apikey2",
            description = "Enter the JWT token in the format '{Bearer token}'.",
            type = SecuritySchemeType.APIKEY,
            bearerFormat = "JWT", // mô tả trong doc openapi
            in = SecuritySchemeIn.HEADER, // require
            paramName = "Authorization-2" // require
        ),
    }
)
@SecurityScheme(
    name = "Bearer",
    description = "Enter the JWT token in the format 'Bearer {token}'.",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer", // require format "bearer"
    bearerFormat = "JWT" // mô tả trong doc openapi
)
@Configuration
public class SwaggerConfig {}
