package com.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// * @OpenAPIDefinition và @SecurityScheme có thể cấu hình ở Application
// * nếu cấu hình ở ngoài Application thì phải có @Configuration
// * nếu không có thì khi thay đổi devtool reload thì nó sẽ không update thay đổi
// * nếu có @Configuration thì khi thay đổi thì devtool reload sẽ update thay đổi
// * nếu cấu hình ở Application thì không cần  @Configuration
// @OpenAPIDefinition
// @SecurityScheme
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
