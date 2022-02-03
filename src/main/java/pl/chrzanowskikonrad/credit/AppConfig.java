package pl.chrzanowskikonrad.credit;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableJpaRepositories("pl.chrzanowskikonrad.credit.*")
public class AppConfig {

//    @Bean
//    public DataSource dataSource() {
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/credit_DB?user=root&password=12345&useUnicode=true" +
//                "  &useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//        dataSource.setUsername( "root" );
//        dataSource.setPassword( "12345" );
//        return dataSource;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setDatabase(Database.MYSQL);
//        jpaVendorAdapter.setGenerateDdl(true);
//        return jpaVendorAdapter;
//    }
//
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
//        lemfb.setDataSource(dataSource());
//        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
//        lemfb.setPackagesToScan("pl.chrzanowskikonrad.CreditApp.*");
//        return lemfb;
//    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate =  new RestTemplate();

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
