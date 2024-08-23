package user.dataaccess.adapter.client.command;

import java.util.Optional;
import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.dataaccess.entities.ClientEntity;
import user.dataaccess.mappers.ClientMapper;
import user.dataaccess.repository.ClientJpaRepository;
import user.domain.application.ports.output.repository.client.command.ClientCommandRepository;
import user.test.exception.CreateException;
import user.test.record.ExceptionResponseRecord;
import user.test.record.request.ClientRequestRecord;
import user.test.record.response.ClientResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientCommandRepositoryImpl implements ClientCommandRepository {

  private final ClientJpaRepository clientJpaRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      ClientRequestRecord clientRequestRecord) {

    ClientEntity entity = getClientEntity(clientRequestRecord);
    ClientResponseRecord saved = ClientMapper.INSTANCE.entityToResponseRecord(
        clientJpaRepository.save(entity));
    return createSuccessResponse(
        "Cliente con CI: " + clientRequestRecord.idNumber() + " registrado con éxito !",
        saved);
  }

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> delete(String idNumber) {
    return clientJpaRepository.findByIdNumber(idNumber)
        .map(this::deleteClient)
        .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
  }

  private ResponseEntity<ExceptionResponseRecord> deleteClient(ClientEntity client) {
    clientJpaRepository.deleteByIdNumber(client.getIdNumber());
    return createSuccessResponse("Cliente : " + client.getName() + " eliminad@",
        ClientMapper.INSTANCE.entityToResponseRecord(client));
  }

  private ClientEntity updateExistingClient(ClientRequestRecord requestRecord) {
    ClientEntity existingEntity =
        clientJpaRepository
            .findByIdNumber(requestRecord.idNumber())
            .orElseThrow(() -> new CreateException("Cliente no encontrado"));

    final StampedLock lock = existingEntity.getLock();
    long stamp = lock.writeLock();
    try {
      existingEntity.setPassword(requestRecord.password());
      existingEntity.setStatus(requestRecord.status());

    } finally {
      lock.unlockWrite(stamp);
    }
    return existingEntity;
  }

  private ExceptionResponseRecord CreateException(String message, Object data) {
    return ExceptionResponseRecord.builder()
        .httpStatus(200)
        .message(message)
        .data(data)
        .build();
  }

  private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
      ClientResponseRecord saved) {
    return new ResponseEntity<>(CreateException(message, saved), HttpStatus.OK);
  }

  private ClientEntity getClientEntity(ClientRequestRecord clientRequestRecord) {
    Optional<ClientEntity> clientOptional = clientJpaRepository.findByIdNumber(
        clientRequestRecord.idNumber());
    return clientOptional.isPresent() ? updateExistingClient(clientRequestRecord)
        : createNewClient(clientRequestRecord);
  }

  private ClientEntity createNewClient(ClientRequestRecord clientRequestRecord) {
    return ClientMapper.INSTANCE.requestRecordToEntity(clientRequestRecord);
  }

  private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
    return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
  }
}
