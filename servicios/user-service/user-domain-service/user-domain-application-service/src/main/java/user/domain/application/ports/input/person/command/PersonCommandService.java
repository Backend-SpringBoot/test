package user.domain.application.ports.input.person.command;

import org.springframework.http.ResponseEntity;
import user.test.record.ExceptionResponseRecord;
import user.test.record.request.PersonRequestRecord;

public interface PersonCommandService {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(PersonRequestRecord personRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(String id);

}
