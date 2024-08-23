package core.dataaccess.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import core.dataaccess.entities.TransactionEntity;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Integer> {

  @Query("select t from TransactionEntity t where "
      + "t.transacitionDate BETWEEN :startDate AND :endDate "
      + "and t.client = :idNumber and t.movementType = :movementType")
  List<TransactionEntity> findByTransacitionDateBetweenAndClientAndMovementType(
      LocalDateTime startDate,
      LocalDateTime endDate, String idNumber, String movementType);

  @Query("SELECT m FROM TransactionEntity m where m.accountNumber = :accountNumber")
  Optional<TransactionEntity> findByAccountNumber(String accountNumber);
}
