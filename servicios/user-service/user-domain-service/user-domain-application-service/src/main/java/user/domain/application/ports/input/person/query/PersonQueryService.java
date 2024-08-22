package user.domain.application.ports.input.person.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import user.test.record.ExceptionResponseRecord;

public interface PersonQueryService {

    ResponseEntity<ExceptionResponseRecord> getUser(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getUsers();

}
