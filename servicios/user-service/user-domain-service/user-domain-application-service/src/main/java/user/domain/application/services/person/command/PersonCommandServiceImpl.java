package user.domain.application.services.person.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.test.record.ExceptionResponseRecord;
import user.test.record.request.PersonRequestRecord;
import user.domain.application.ports.input.person.command.PersonCommandService;
import user.domain.application.ports.output.repository.person.commad.PersonCommandRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonCommandServiceImpl implements PersonCommandService {

    private final PersonCommandRepository personCommandRepository;

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            PersonRequestRecord personRequestRecord) {
        return personCommandRepository.createOrUpdate(personRequestRecord);
    }

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> delete(String id) {
        return personCommandRepository.delete(id);
    }
}
