package me.ubmagh.springmulticonnectorms;

import com.github.javafaker.Faker;
import jakarta.xml.ws.Endpoint;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.enums.AccountTypeEnum;
import me.ubmagh.springmulticonnectorms.services.AccountService;
import me.ubmagh.springmulticonnectorms.web.Soap.SoapAccountWebService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringMultiConnectorMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMultiConnectorMsApplication.class, args);
    }



    @Value("${server.soap-service-url}")
    private String url;

    @Bean(name = "SoapEndPointServiceBean")
    public Endpoint endpoint(AccountService accountService) {
        Endpoint endpoint = Endpoint.publish(url, new SoapAccountWebService(accountService));
        log.info(" 🚀 Soap service started on :  "+url);
        return endpoint;
    }


    @Bean
    CommandLineRunner fillDb(AccountService accountService) {
        Faker faker = new Faker();
        String[] enums = new String[]{"ORGANISATION", "USER"};
        return args -> {
            int rand = faker.random().nextInt(5, 20);
            while (rand-- > 1) {
                accountService.createAccount(
                        new AccountRequestDTO(
                                faker.avatar().image(),
                                faker.name().username(),
                                "123",
                                faker.internet().emailAddress(),
                                faker.internet().url(),
                                AccountTypeEnum.valueOf(enums[faker.random().nextInt(0, 1)]),
                                ""
                        )
                );
            }
        };
    }

}
