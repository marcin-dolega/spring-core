package pl.dolega.springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class SpringCoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringCoreApplication.class, args);

	}

}
