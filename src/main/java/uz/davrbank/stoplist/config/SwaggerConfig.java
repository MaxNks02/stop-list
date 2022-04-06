package uz.davrbank.stoplist.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(getInfo());
    }

    private Info getInfo() {
        return new Info()
                .title("Davr Bank Stop List Api")
                .description("Stop List module for CBS")
                .version("Version: 1")
                .contact(getContact());
    }

    private Contact getContact() {
        return new Contact().name("Davr Bank IT Team.");
    }
}
