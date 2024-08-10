package test.base.contenedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients(basePackages = "test")
@SpringBootApplication(scanBasePackages = "test")
@EnableJpaRepositories(basePackages = {"test"})
@EntityScan(basePackages = {"test"})
public class TestServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(TestServiceApplication.class, args);
  }
}
