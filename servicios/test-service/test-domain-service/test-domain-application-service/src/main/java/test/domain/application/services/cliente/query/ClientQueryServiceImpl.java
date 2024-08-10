package test.domain.application.services.cliente.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.domain.application.ports.input.client.query.ClientQueryService;
import test.domain.application.ports.output.repository.client.query.ClientQueryRepository;
import test.test.record.ExceptionResponseRecord;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientQueryRepository clientQueryRepository;

    @Override
    public ResponseEntity<ExceptionResponseRecord> getClient(String id) {
        return clientQueryRepository.getClient(id);
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getClients() {
        return clientQueryRepository.getCliets();
    }
}
