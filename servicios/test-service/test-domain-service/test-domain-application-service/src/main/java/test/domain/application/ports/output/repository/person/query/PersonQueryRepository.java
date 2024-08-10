package test.domain.application.ports.output.repository.person.query;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;

import java.util.List;

public interface PersonQueryRepository {

    ResponseEntity<ExceptionResponseRecord> getUser(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getUsers();
}
