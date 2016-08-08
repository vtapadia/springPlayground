package com.vtapadia.experiments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class DocConfig {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.vtapadia.experiments"))
                .build()
                .apiInfo(new ApiInfo("Spring Playground", "Experimental project for Spring boot", "0.1",
                        "Terms of Service URL",
                        new Contact("Varesh Tapadia", "http://vtapadia.github.io/", "varesh.tapadia@gmail.com"),
                        "Apache License 2.0", "http://www.apache.org/licenses/LICENSE-2.0"));
    }
}
