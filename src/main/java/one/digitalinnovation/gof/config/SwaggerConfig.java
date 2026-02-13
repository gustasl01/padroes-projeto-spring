package one.digitalinnovation.gof.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Clientes")
                        .version("1.0.0")
                        .description("API RESTful para gerenciamento de clientes com integração ao ViaCEP. "
                                + "Este projeto demonstra a implementação de padrões de projeto (Singleton, Strategy/Repository e Facade) "
                                + "utilizando Spring Boot, Spring Data JPA, H2 Database e OpenFeign."));
    }
}
