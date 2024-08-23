package user.feign;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "core", url = "${remote.microservice.core-service-url}")
public interface CoreFeignClientService {

  @GetMapping(value = "/api/core/query/cuentas/by/{idNumber}", consumes = MediaType.APPLICATION_JSON_VALUE)
  List<LinkedHashMap<String, Object>> findByIdNumber(@PathVariable("idNumber") String idNumber);
}
