package core.remote.adapter;

import core.domain.application.ports.output.remote.UserRemoteService;
import core.record.ExceptionResponseRecord;
import core.remote.feign.UserRemoteFeignClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRemoteFeignClientServiceImpl implements UserRemoteService {

  private final UserRemoteFeignClientService userRemoteFeignClientService;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getUser(String id) {
    return userRemoteFeignClientService.getUser(id);
  }

  @Override
  public ExceptionResponseRecord getClient(String id) {
    return userRemoteFeignClientService.getClient(id);
  }
}
