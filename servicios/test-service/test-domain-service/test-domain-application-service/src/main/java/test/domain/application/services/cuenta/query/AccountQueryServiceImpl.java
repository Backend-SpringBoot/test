package test.domain.application.services.cuenta.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.domain.application.ports.input.account.query.AccountQueryService;
import test.domain.application.ports.output.repository.account.query.AccountQueryRepository;
import test.test.record.ExceptionResponseRecord;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountQueryRepository accountQueryRepository;

    @Override
    public ResponseEntity<ExceptionResponseRecord> getAccount(String id) {
        return accountQueryRepository.getAccount(id);
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getAccounts() {
        return accountQueryRepository.getAccounts();
    }
}
