package com.lk.cursomc.config;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableSwagger2
public class swaggerConfig {

    // Personalizar Mensagens Globais na Documentação
    private final ResponseMessage m201 = customMessage1();
    private final ResponseMessage m204put = simpleMessage(204, "Atualização OK");
    private final ResponseMessage m204del = simpleMessage(204, "Exclusão OK");
    private final ResponseMessage m403 = simpleMessage(403, "Não Autorizado");
    private final ResponseMessage m404 = simpleMessage(404, "Não Encontrado");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de Validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro Inesperado");


    // objeto de configuração do swagger
    @Bean
    public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)

                    .useDefaultResponseMessages(false)  // desabilita as respostas Default.
                    // Descreve quais as mensagens podem aparecer para cada verbo Rest.
                    .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                    .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                    .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
                    .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))

                    .select()
                    //.apis(RequestHandlerSelectors.any())    // quais os pacotes o swagger deve rastrear
                    .apis(RequestHandlerSelectors.basePackage("com.lk.cursomc.resource"))
                    .paths(PathSelectors.any())         // quais caminhos deve rastrear
                    .build()
                    .apiInfo(apiInfo());
    }

    // personalizar informações da API na documentação do Swagger.
    private ApiInfo apiInfo() {
        return new ApiInfo("API do Curso de Spring-Boot",
                "Esta API é utilizada no curso de Spring-Boot do prof. Nelio Alves",
                "Versão 1.0",
                "https://www.udemy.com/terms",
                new Contact("Alex Leko", "udemy.com/user/nelio-alves", "lk.alexds@gmail.com"),
                "Permitido o uso para estudantes com fins acadêmicos",
                "https://www.udemy.com/terms",
                Collections.emptyList() // extensões de uso na API
        );
    }

    // Personalizar Mensagens Globais na Documentação
    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }

    // Response Headers Personalizado para requisição [POST]
    private ResponseMessage customMessage1() {

        // cria uma lista estatica para o cabeçalho [Location] com os valores descritos.
        Map<String, Header> map = new HashMap<>();
        map.put("location",
                new Header("location", "URI do novo recurso", new ModelRef("string"))
        );

        // personaliza a documentação para as Response com StatusCode = 201
        return new ResponseMessageBuilder()
                .code(201)
                .message("Recurso Criado")
                .headersWithDescription(map)
                .build();
    }

    // === PERSONALIZAÇÃO POR @ANNOTATIONS ===

    // Descrição Personalizada para a nomenclatura dos EndPoints.
    // Inserir no endpoint a @Annotation ...
    // @ApiOperation(value = "Busca categoria por Id")

    // Mensagem Personalizada para endpoint Especifico.
    // Sobrescreve a mensagem Global para o statusCode.
    // Inserir no endpoint a @Annotation ...
    //    @ApiResponses(value = {
    //            @ApiResponse(code = 400, message = "Não é possivel excluir uma categoria que possui produtos"),
    //            @ApiResponse(code = 404, message = "Código inexistente")
    //    })

}
