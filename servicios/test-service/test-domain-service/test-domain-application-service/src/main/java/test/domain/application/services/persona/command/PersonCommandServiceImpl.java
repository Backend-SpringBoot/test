package test.domain.application.services.persona.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.domain.application.ports.input.person.command.PersonCommandService;
import test.domain.application.ports.output.repository.person.commad.PersonCommandRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.PersonRequestRecord;

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
