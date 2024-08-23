package user.dataaccess.adapter.client.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.dataaccess.entities.ClientEntity;
import user.dataaccess.mappers.ClientMapper;
import user.dataaccess.repository.ClientJpaRepository;
import user.domain.application.ports.output.repository.client.query.ClientQueryRepository;
import user.test.record.ExceptionResponseRecord;
import user.test.record.response.ClientResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientQueryRepositoryImpl implements ClientQueryRepository {

  private final ClientJpaRepository clientJpaRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getClient(String id) {
    return clientJpaRepository.findByIdNumber(id)
        .map(this::getClientResponse)
        .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getCliets() {
    List<ClientEntity> clientEntities = clientJpaRepository.findAll();
    List<ClientResponseRecord> data = ClientMapper.INSTANCE.entitiesToResponseRecords(
        clientEntities);
    return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(200)
        .message(message)
        .data(o)
        .build();
  }

  private ResponseEntity<ExceptionResponseRecord> getClientResponse(ClientEntity client) {
    ClientResponseRecord data = ClientMapper.INSTANCE.entityToResponseRecord(client);

    return createSuccessResponse("Correcto!", data);
  }

  private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
    return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
  }

  private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
      ClientResponseRecord data) {
    return new ResponseEntity<>(CreateException(message, data), HttpStatus.OK);
  }
}
