package user.domain.application.ports.output.repository.person.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import user.test.record.ExceptionResponseRecord;

public interface PersonQueryRepository {

    ResponseEntity<ExceptionResponseRecord> getUser(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getUsers();
}
