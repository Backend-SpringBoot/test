package core.dataaccess.entities;

import core.dataaccess.entities.core.impl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
//@Audited
@Table(
    name = "account",
    schema = "core"
)
@EntityListeners(AuditingEntityListener.class)
public class AccountEntity extends AbstractEntity<Integer, Integer> {

  @NotBlank(message = "{Número account no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25 ")
  @Column(name = "accountNumber", nullable = false)
  private String accountNumber;

  @NotBlank(message = "{Tipo account no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "accountType", nullable = false)
  private String accountType;

  @Column(name = "saldoInicial", nullable = false)
  @NotNull(message = "Saldo inicial no debe ser nulo")
  @PositiveOrZero(message = "Saldo inicial debe ser positivo o cero")
  private double initialBalance;

  @Size(max = 13, message = "{Tamaño maximo de 13")
  @Column(name = "idNumber", nullable = false)
  private String idNumber;

}
