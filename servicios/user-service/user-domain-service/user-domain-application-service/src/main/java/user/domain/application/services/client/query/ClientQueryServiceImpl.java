package user.domain.application.services.client.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.test.record.ExceptionResponseRecord;
import user.domain.application.ports.input.client.query.ClientQueryService;
import user.domain.application.ports.output.repository.client.query.ClientQueryRepository;

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
