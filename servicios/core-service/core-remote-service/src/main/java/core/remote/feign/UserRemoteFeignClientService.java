package core.remote.feign;

import core.record.ExceptionResponseRecord;
import core.remote.adapter.UserRemoteFeignClientServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "person", url = "${remote.microservice.user-service-url}", fallbackFactory = UserRemoteFeignClientServiceImpl.class, configuration = FormConfiguration.class)
public interface UserRemoteFeignClientService {

  @GetMapping(value = "/api/user/query/person/{id}", consumes = "application/json")
  ResponseEntity<ExceptionResponseRecord> getUser(@PathVariable("id") String id);

  @GetMapping(value = "/api/user/query/clientes/{id}", consumes = "application/json")
  ExceptionResponseRecord getClient(@PathVariable("id") String id);
}
