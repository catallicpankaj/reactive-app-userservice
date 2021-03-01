package com.xcomp.sample.configs;

import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigs  implements WebFluxConfigurer {
    private static final String SWAGGER_UI_PATH = "/swagger-ui";

    @Bean
    public Docket api() {
       
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(Mono.class, Flux.class, Publisher.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xcomp.sample"))
                .paths(PathSelectors.any())
                .build();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(SWAGGER_UI_PATH+"**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler(SWAGGER_UI_PATH+"/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}

