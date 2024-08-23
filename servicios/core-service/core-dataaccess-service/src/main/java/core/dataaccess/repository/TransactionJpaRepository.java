package core.dataaccess.repository;

import core.dataaccess.entities.TransactionEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Integer> {

  @Query("select t from TransactionEntity t where "
      + "t.transacitionDate BETWEEN :startDate AND :endDate "
      + "and t.client = :idNumber and t.movementType = :movementType and t.accountType = :accountType")
  List<TransactionEntity> findByTransacitionDateBetweenAndClientAndMovementType(
      LocalDateTime startDate,
      LocalDateTime endDate, String idNumber, String movementType, String accountType);

  @Query("SELECT m FROM TransactionEntity m where m.accountNumber = :accountNumber")
  Optional<TransactionEntity> findByAccountNumber(String accountNumber);
}
