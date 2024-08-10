package test.domain.application.services.persona.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.domain.application.ports.input.person.query.PersonQueryService;
import test.domain.application.ports.output.repository.person.query.PersonQueryRepository;
import test.test.record.ExceptionResponseRecord;

import java.util.List;

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
