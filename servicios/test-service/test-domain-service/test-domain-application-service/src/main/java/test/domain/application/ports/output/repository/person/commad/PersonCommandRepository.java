package test.domain.application.ports.output.repository.person.commad;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.PersonRequestRecord;

public interface PersonCommandRepository {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(PersonRequestRecord personRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(String id);
}
