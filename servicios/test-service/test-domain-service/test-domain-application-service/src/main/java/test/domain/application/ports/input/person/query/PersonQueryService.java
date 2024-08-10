package test.domain.application.ports.input.person.query;

import java.util.List;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;

public interface PersonQueryService {

    ResponseEntity<ExceptionResponseRecord> getUser(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getUsers();

}
