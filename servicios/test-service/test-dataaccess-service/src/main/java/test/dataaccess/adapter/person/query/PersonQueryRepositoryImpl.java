package test.dataaccess.adapter.person.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dataaccess.entities.PersonEntity;
import test.dataaccess.mappers.PersonMapper;
import test.dataaccess.repository.PersonJpaRepository;
import test.domain.application.ports.output.repository.person.query.PersonQueryRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.response.PersonResponseRecord;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonQueryRepositoryImpl implements PersonQueryRepository {

    private final PersonJpaRepository personJpaRepository;

    @Override
    public ResponseEntity<ExceptionResponseRecord> getUser(String id) {
        return personJpaRepository.findByIdNumber(id)
                .map(this::getPersonResponse)
                .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getUsers() {
        List<PersonEntity> personEntities = personJpaRepository.findAll();
        List<PersonResponseRecord> data = PersonMapper.INSTANCE.entitiesToResponseRecords(
                personEntities);
        return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
    }

    private ExceptionResponseRecord CreateException(String message, Object o) {
        return ExceptionResponseRecord.builder()
                .httpStatus(HttpStatus.ACCEPTED)
                .message(message)
                .data(o)
                .build();
    }

    private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
        return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
    }

    private ResponseEntity<ExceptionResponseRecord> getPersonResponse(PersonEntity person) {
        PersonResponseRecord data = PersonMapper.INSTANCE.entityToResponseRecord(person);
        return createSuccessResponse("Correcto!", data);
    }

    private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
                                                                          PersonResponseRecord data) {
        return new ResponseEntity<>(CreateException(message, data), HttpStatus.OK);
    }
}
