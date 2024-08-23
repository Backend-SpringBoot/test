package user.dataaccess.adapter.client.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import user.dataaccess.entities.ClientEntity;
import user.dataaccess.repository.ClientJpaRepository;
import user.test.record.ExceptionResponseRecord;

class ClientQueryRepositoryImplTest {

  @Test
  public void clientFound() {
    ClientJpaRepository clientJpaRepository = mock(ClientJpaRepository.class);
    ClientQueryRepositoryImpl clientQueryRepository = new ClientQueryRepositoryImpl(
        clientJpaRepository);
    ClientEntity clientEntity = new ClientEntity();
    clientEntity.setIdNumber("123");
    clientEntity.setPassword("password");
    clientEntity.setName("Juan");
    when(clientJpaRepository.findByIdNumber("123")).thenReturn(Optional.of(clientEntity));

    ResponseEntity<ExceptionResponseRecord> response = clientQueryRepository.getClient("123");

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody().data());
  }

  @Test
  public void clientNullOrEmpty() {
    ClientJpaRepository clientJpaRepository = mock(ClientJpaRepository.class);
    ClientQueryRepositoryImpl clientQueryRepository = new ClientQueryRepositoryImpl(
        clientJpaRepository);

    ResponseEntity<ExceptionResponseRecord> responseNull = clientQueryRepository.getClient(null);
    ResponseEntity<ExceptionResponseRecord> responseEmpty = clientQueryRepository.getClient("");

    assertEquals(HttpStatus.OK, responseNull.getStatusCode());
    assertEquals("No se encontró ningún registro", responseNull.getBody().message());
    assertEquals(HttpStatus.OK, responseEmpty.getStatusCode());
    assertEquals("No se encontró ningún registro", responseEmpty.getBody().message());
  }
}