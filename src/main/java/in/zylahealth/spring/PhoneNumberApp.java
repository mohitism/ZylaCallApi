package in.zylahealth.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"in.zylahealth.spring","in.zylaheath.spring.repository","in.zylaheath.spring.entity"})
@EnableJpaRepositories("in.zylaheath.spring.repository")
@EntityScan("in.zylaheath.spring.entity")
public class PhoneNumberApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(PhoneNumberApp.class, args);
	}

}
