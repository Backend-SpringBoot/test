package user.domain.application.services.person.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.test.record.ExceptionResponseRecord;
import user.domain.application.ports.input.person.query.PersonQueryService;
import user.domain.application.ports.output.repository.person.query.PersonQueryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonQueryServiceImpl implements PersonQueryService {

    private final PersonQueryRepository personQueryRepository;

    @Override
    public ResponseEntity<ExceptionResponseRecord> getUser(String id) {
        return personQueryRepository.getUser(id);
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getUsers() {
        return personQueryRepository.getUsers();
    }
}
