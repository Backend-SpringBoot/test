package test.dataaccess.adapter.person.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dataaccess.entities.PersonEntity;
import test.dataaccess.mappers.PersonMapper;
import test.dataaccess.repository.PersonJpaRepository;
import test.domain.application.ports.output.repository.person.commad.PersonCommandRepository;
import test.test.exception.CreateException;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.PersonRequestRecord;
import test.test.record.response.PersonResponseRecord;

import java.util.Optional;
import java.util.concurrent.locks.StampedLock;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonCommandRepositoryImpl implements PersonCommandRepository {

    private final PersonJpaRepository personJpaRepository;

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            PersonRequestRecord personRequestRecord) {

        PersonEntity entity = getPersonEntity(personRequestRecord);
        PersonResponseRecord saved = PersonMapper.INSTANCE.entityToResponseRecord(
                personJpaRepository.save(entity));
        return createSuccessResponse(
                "Cuenta almacenada para el usuario: ",
                saved);
    }

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> delete(String id) {
        return personJpaRepository.findByIdNumber(id)
                .map(this::deletePerson)
                .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
    }

    private ResponseEntity<ExceptionResponseRecord> deletePerson(PersonEntity person) {
        personJpaRepository.deletePersonEntityByIdNumber(person.getIdNumber());
        return createSuccessResponse("Persona eliminada",
                PersonMapper.INSTANCE.entityToResponseRecord(person));
    }

    private PersonEntity updateExistingPerson(PersonRequestRecord requestRecord) {
        PersonEntity existingEntity =
                personJpaRepository
                        .findById(requestRecord.id())
                        .orElseThrow(() -> new CreateException("Persona no encontrada"));

        final StampedLock lock = existingEntity.getLock();
        long stamp = lock.writeLock();
        try {
            existingEntity.setName(requestRecord.name());
            existingEntity.setGender(requestRecord.gender());
            existingEntity.setStatus(requestRecord.status());
            existingEntity.setAge(requestRecord.age());
            existingEntity.setIdNumber(requestRecord.idNumber());
            existingEntity.setAddress(requestRecord.address());
            existingEntity.setPhone(requestRecord.phone());

        } finally {
            lock.unlockWrite(stamp);
        }
        return existingEntity;
    }

    private PersonEntity getPersonEntity(PersonRequestRecord personRequestRecord) {
        Optional<PersonEntity> personOptional = personJpaRepository.findByIdNumber(
                personRequestRecord.idNumber());
        return personOptional.isPresent() ? updateExistingPerson(personRequestRecord)
                : createNewPerson(personRequestRecord);
    }

    private PersonEntity createNewPerson(PersonRequestRecord personRequestRecord) {
        return PersonMapper.INSTANCE.requestRecordToEntity(personRequestRecord);
    }

    private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
                                                                          PersonResponseRecord saved) {
        return new ResponseEntity<>(CreateException(message, saved), HttpStatus.OK);
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
}
