package core.domain.application.services.account.command;

import core.domain.application.ports.input.account.command.AccountCommandService;
import core.domain.application.ports.output.repository.account.command.AccountCommandRepository;
import core.record.ExceptionResponseRecord;
import core.record.request.AccountRequestRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountCommandServiceImpl implements AccountCommandService {

  private final AccountCommandRepository accountCommandRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      AccountRequestRecord accountRequestRecord) {
    return accountCommandRepository.createOrUpdate(accountRequestRecord);
  }

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> delete(String id) {
    return accountCommandRepository.delete(id);
  }
}
