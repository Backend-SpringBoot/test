package user.adapter;

import java.util.LinkedHashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.domain.application.ports.output.remote.CoreRemoteService;
import user.feign.CoreFeignClientService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CoreRemoteServiceImpl implements CoreRemoteService {

  private final CoreFeignClientService coreFeignClientService;

  @Override
  public List<LinkedHashMap<String, Object>> findByIdNumber(String id) {
    return coreFeignClientService.findByIdNumber(id);
  }
}
