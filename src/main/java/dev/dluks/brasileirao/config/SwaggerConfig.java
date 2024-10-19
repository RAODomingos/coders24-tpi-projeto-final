package dev.dluks.brasileirao.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    static ExternalDocumentation gitHubLink;
    static Info infos;

    public static void setGitHubLink() {
        gitHubLink = new ExternalDocumentation()
                .description("GitHub Link")
                .url("https://github.com/dluks82/coders24-tpi-projeto-final");
    }

    public static void setInfos() {
        infos = new Info()
                .title("API Brasileirão")
                .version("1.0")
                .description("""
                    Esta API é parte de um projeto desenvolvido para aplicar técnicas de programação funcional, paralela e assíncrona no contexto de um sistema orientado a objetos. Utilizando tecnologias modernas do Java, como Streams, Optional e threads, o objetivo é otimizar e escalar a execução das operações do sistema, garantindo uma arquitetura mais robusta e performática.
                    
                    Desenvolvida por:
                      - Diogo Oliveira
                      - Isaque Menezes
                      - Rômulo Domingos
                    """);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        setGitHubLink();
        setInfos();
        return new OpenAPI()
                .info(infos)
                .externalDocs(gitHubLink);
    }
}
