package test.domain.application.ports.input.person.command;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.PersonRequestRecord;

public interface PersonCommandService {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(PersonRequestRecord personRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(String id);

}
