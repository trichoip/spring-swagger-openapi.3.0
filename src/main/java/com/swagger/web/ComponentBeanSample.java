package com.swagger.web;

import com.swagger.Constants.SwaggerTagConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Tag(name = SwaggerTagConstants.COMPONENT_BEAN)
public class ComponentBeanSample {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ConfigurationBeanService configurationBeanService;

    @GetMapping("/component")
    public ResponseEntity<?> component() {
        componentService.doSome();
        return ResponseEntity.ok(componentService.getValueFrom());
    }

    @GetMapping("/bean")
    public ResponseEntity<?> bean() {
        configurationBeanService.doSome();
        return ResponseEntity.ok(configurationBeanService.getValueFrom());
    }
}

//=================================================================
// 3 cai duoi deu co tac dung nhu @Component
// @Controller
// @Repository
// @Service
@Component // tiem DI
class ComponentService {

    public String getValueFrom() {
        return "Component string";
    }

    public void doSome() {
        System.out.println("Component void");
    }
}

//=================================================================

class ConfigurationBeanService {

    public String getValueFrom() {
        return "ConfigurationBean string";
    }

    public void doSome() {
        System.out.println("ConfigurationBean void");
    }
}

@Configuration
class ConfigurationBean {

    @Bean
    public ConfigurationBeanService configurationBeanService() {
        System.out.println("============================ConfigurationBean============================");
        return new ConfigurationBeanService();
    }
}
