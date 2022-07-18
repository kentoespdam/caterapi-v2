package com.kentoes.caterapi.config;

import com.kentoes.caterapi.controllers.customer.CustomerSaveReq;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableWebMvc
public class SpringFoxConfig implements WebMvcConfigurer {
    //    replace datetime format
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder dateTime = new Jackson2ObjectMapperBuilder()
                .indentOutput(false)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        converters.add(new MappingJackson2HttpMessageConverter(dateTime.build()));
        Jackson2ObjectMapperBuilder date = new Jackson2ObjectMapperBuilder()
                .indentOutput(false)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        converters.add(new MappingJackson2HttpMessageConverter(date.build()));
        Jackson2ObjectMapperBuilder time = new Jackson2ObjectMapperBuilder()
                .indentOutput(false)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        converters.add(new MappingJackson2HttpMessageConverter(time.build()));
    }

    @Bean
    public Docket customerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Customers-API")
                .select()
                .paths(customersPath())
                .build()
                .ignoredParameterTypes(Errors.class)
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket hasilBacaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Hasil Baca-API")
                .select()
                .paths(hasilBacaPath())
                .build()
                .ignoredParameterTypes(Errors.class)
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket zonesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("zone-API")
                .select()
                .paths(zonesPath())
                .build()
                .ignoredParameterTypes(Errors.class)
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket usersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Users-API")
                .select()
                .paths(usersPath())
                .build()
                .ignoredParameterTypes(Errors.class)
                .apiInfo(apiInfo());
    }

    private Predicate<String> customersPath() {
        return regex(".*/api/customers.*");
    }

    private Predicate<String> hasilBacaPath() {
        return regex(".*/api/hasil_baca.*");
    }

    private Predicate<String> zonesPath() {
        return regex(".*/api/zones.*");
    }

    private Predicate<String> usersPath() {
        return regex(".*/api/users.*");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Coba API",
                "Uji coba bikin spring boot",
                "1.0.0",
                "API TOS",
                new Contact(
                        "Kent-Os",
                        "http://kentoes.com",
                        "k3ntoes.android@gmail.com"
                ),
                "Apache License",
                "http://apache.com",
                Collections.emptyList()
        );
        return apiInfo;
    }
}
