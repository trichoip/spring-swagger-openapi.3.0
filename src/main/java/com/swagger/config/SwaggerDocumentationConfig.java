package com.swagger.config;

import com.swagger.Constants.SwaggerTagConstants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.Arrays;
import java.util.HashMap;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("Swagger for System Bean")
                    .description(
                        "_This is a list of endpoints and documentation for `REST API for System`_" +
                        "\n - [https://editor.swagger.io/](https://editor.swagger.io/) \n" +
                        "- [https://petstore3.swagger.io/](https://petstore3.swagger.io/?filter=pet)"
                    )
                    .version("7.0")
                    .termsOfService("policy")
                    .contact(new Contact().email("github@email.com").name("Githubb").url("https://github.com").extensions(null))
                    .license(new License().name("Github license").url("policy").extensions(null))
                    .extensions(null)
            )
            .externalDocs(new ExternalDocumentation().url("https://github.com").description("this is externalDocs").extensions(null))
            .tags(
                Arrays.asList(
                    new Tag().name("Tag 1").description("REST API endpoints for Tag 1"),
                    new Tag().name("Security Requirement").description("REST API endpoints for Security Requirement"),
                    new Tag()
                        .name(SwaggerTagConstants.COMPONENT_BEAN)
                        .description("REST API endpoints for Component")
                        .externalDocs(new ExternalDocumentation().url("https://api.github.com").description("github io").extensions(null))
                        .extensions(null),
                    new Tag()
                        .name(SwaggerTagConstants.GITHUB_API)
                        .description("REST API endpoints for Github Api")
                        .externalDocs(new ExternalDocumentation().url("https://api.github.com").description("github io"))
                )
            )
            .servers(
                Arrays.asList(
                    new Server().url("http://localhost:8080"),
                    new Server()
                        .url("{SCHEMA}://{HOST}{PORT}")
                        .description("Server config 1")
                        .variables(
                            new ServerVariables()
                                .addServerVariable(
                                    "HOST",
                                    new ServerVariable()
                                        .description("this is host")
                                        ._enum(
                                            Arrays.asList(
                                                "json.schemastore.org",
                                                "api.github.com",
                                                "etransportation-webapp-api.azurewebsites.net",
                                                "localhost"
                                            )
                                        )
                                        ._default("localhost")
                                )
                                .addServerVariable(
                                    "PORT",
                                    new ServerVariable().description("this is port")._enum(Arrays.asList("/", ":80", ":8080"))._default(":8080")
                                )
                                .addServerVariable(
                                    "SCHEMA",
                                    new ServerVariable().description("this is schema")._enum(Arrays.asList("http", "https"))._default("http")
                                )
                                .addServerVariable("Sample", new ServerVariable().description("this is sample")._enum(null)._default("https"))
                        )
                        .extensions(null),
                    new Server()
                        .url("{URL}")
                        .description("Server config url")
                        .variables(
                            new ServerVariables()
                                .addServerVariable(
                                    "URL",
                                    new ServerVariable()
                                        .description("this is url")
                                        ._enum(
                                            Arrays.asList(
                                                "https://json.schemastore.org",
                                                "https://api.github.com",
                                                "https://etransportation-webapp-api.azurewebsites.net",
                                                "http://localhost:8080"
                                            )
                                        )
                                        ._default("https://api.github.com")
                                )
                        ),
                    new Server().url("https://json.schemastore.org/launchsettings.json"),
                    new Server().url("https://api.github.com")
                )
            )
            .extensions(
                new HashMap<String, Object>() {
                    {
                        put(
                            "x-dog",
                            new HashMap<String, Object>() {
                                {
                                    put("name", "cat meow");
                                    put("age", 20);
                                    put("flow", true);
                                }
                            }
                        );
                        put("x-alive", true);
                        put("x-die", "true");
                    }
                }
            )
            .security(
                Arrays.asList(
                    new SecurityRequirement().addList("ApikeyBean", Arrays.asList("read", "write")), //
                    new SecurityRequirement().addList("BearerBean")
                )
            )
            .components(
                new Components()
                    .securitySchemes(
                        new HashMap<String, SecurityScheme>() {
                            {
                                put(
                                    "ApikeyBean",
                                    new SecurityScheme()
                                        .description("Enter the JWT token in the format '{Bearer token}'.")
                                        .type(Type.APIKEY)
                                        .bearerFormat("JWT JWT")
                                        .in(In.HEADER)
                                        .name("Authorization-y")
                                );
                            }
                        }
                    )
                    .addSecuritySchemes(
                        "BearerBean",
                        new SecurityScheme()
                            .description("Enter format 'Bearer {token}'.") //
                            .type(Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT JWT")
                    )
                // .callbacks(null)
                // .examples(null)
                // .extensions(null)
                // .headers(null)
                // .links(null)
                // .parameters(null)
                // .pathItems(null)
                // .requestBodies(null)
                // .responses(null)
                // .schemas(null)
            );
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi
            .builder()
            .displayName("swagger /github")
            .group("swagger-paths-github")
            .pathsToMatch("/github/**", "/api/**")
            .addOpenApiCustomizer(openApi ->
                openApi
                    .info(openApi().getInfo())
                    .externalDocs(openApi().getExternalDocs())
                    .tags(openApi().getTags())
                    .servers(openApi().getServers())
                    .extensions(openApi().getExtensions())
                    .security(openApi().getSecurity())
            )
            // .addOpenApiMethodFilter(null)
            // .addOperationCustomizer(null)
            // .addRouterOperationCustomizer(null)
            .build();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("swagger-all").pathsToMatch("/**").build();
    }

    @Bean
    public GroupedOpenApi packageApi() {
        return GroupedOpenApi.builder().group("swagger-package").packagesToScan("com.swagger.web").build();
    }
}
