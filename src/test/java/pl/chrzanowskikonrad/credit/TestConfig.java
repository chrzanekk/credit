package pl.chrzanowskikonrad.credit;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class TestConfig {
//    @Bean
//    RestTemplate restTemplate() {
//        RestTemplate restTemplate =  new RestTemplate();
//
//        restTemplate.getMessageConverters()
//                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        return restTemplate;
//    }
//
//    @Bean
//    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
//        return builder;
//    }
}
