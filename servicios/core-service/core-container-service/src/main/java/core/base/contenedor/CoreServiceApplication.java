package core.base.contenedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients(basePackages = "core")
@SpringBootApplication(scanBasePackages = "core")
@EnableJpaRepositories(basePackages = {"core"})
@EntityScan(basePackages = {"core"})
public class CoreServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(CoreServiceApplication.class, args);
  }
}
