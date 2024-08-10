package test.dataaccess.adapter.client.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dataaccess.entities.ClientEntity;
import test.dataaccess.mappers.ClientMapper;
import test.dataaccess.repository.ClientJpaRepository;
import test.domain.application.ports.output.repository.client.command.ClientCommandRepository;
import test.test.exception.CreateException;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.ClientRequestRecord;
import test.test.record.response.ClientResponseRecord;

import java.util.Optional;
import java.util.concurrent.locks.StampedLock;

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
                "Cuenta almacenada para el usuario: " + clientRequestRecord.name(),
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
                .httpStatus(HttpStatus.ACCEPTED)
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
