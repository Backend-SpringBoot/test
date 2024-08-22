package core.domain.application.services.account.query;

import core.domain.application.ports.input.account.query.AccountQueryService;
import core.domain.application.ports.output.repository.account.query.AccountQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.record.ExceptionResponseRecord;

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
