package user.domain.application.ports.output.repository.person.commad;

import org.springframework.http.ResponseEntity;
import user.test.record.ExceptionResponseRecord;
import user.test.record.request.PersonRequestRecord;

public interface PersonCommandRepository {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(PersonRequestRecord personRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(String id);
}
