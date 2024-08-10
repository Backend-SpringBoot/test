package test.dataaccess.adapter.client.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dataaccess.entities.ClientEntity;
import test.dataaccess.mappers.ClientMapper;
import test.dataaccess.repository.ClientJpaRepository;
import test.domain.application.ports.output.repository.client.query.ClientQueryRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.response.ClientResponseRecord;

import java.util.List;

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
                .httpStatus(HttpStatus.ACCEPTED)
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
