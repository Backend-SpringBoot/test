package test.domain.application.services.cliente.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.domain.application.ports.input.client.command.ClientCommandService;
import test.domain.application.ports.output.repository.client.command.ClientCommandRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.ClientRequestRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ClientCommandRepository clientCommandRepository;

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            ClientRequestRecord clientRequestRecord) {
        return clientCommandRepository.createOrUpdate(clientRequestRecord);
    }

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> delete(String idNumber) {
        return clientCommandRepository.delete(idNumber);
    }
}
