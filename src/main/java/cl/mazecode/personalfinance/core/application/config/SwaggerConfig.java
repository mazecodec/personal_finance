package cl.mazecode.personalfinance.core.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
//@EnableSwagger2
@EnableWebSecurity
@ComponentScan("cl.mazecode.personalfinance.presentation.*")
public class SwaggerConfig {
    /**
     * Method to set paths to be included through swagger
     *
     * @return Docket
     */

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(new ApiInfoBuilder()
                        .title("Swagger Super")
                        .description("Swagger Description details")
                        .version("1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cl.mazecode.personalfinance.presentation"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Method to set swagger info
     *
     * @return ApiInfoBuilder
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("")
                                   .description("")
                                   .version("1.0")
                                   .build();
    }
}
