package pl.dolega.springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class SpringCoreApplication {

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");

		SpringApplication.run(SpringCoreApplication.class, args);
	}

}
