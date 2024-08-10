package test.domain.application.services.cuenta.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.domain.application.ports.input.account.command.AccountCommandService;
import test.domain.application.ports.output.repository.account.command.AccountCommandRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.AccountRequestRecord;

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
    public ResponseEntity<ExceptionResponseRecord> delete(Integer id) {
        return accountCommandRepository.delete(id);
    }
}
