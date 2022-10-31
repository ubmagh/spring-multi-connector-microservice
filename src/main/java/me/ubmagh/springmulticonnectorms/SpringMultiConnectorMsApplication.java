package me.ubmagh.springmulticonnectorms;

import com.github.javafaker.Faker;
import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.enums.AccountTypeEnum;
import me.ubmagh.springmulticonnectorms.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMultiConnectorMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMultiConnectorMsApplication.class, args);
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
