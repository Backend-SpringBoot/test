package user.dataaccess.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
//@Audited
@Table(
        name = "client",
        schema = "test"
)
@EntityListeners(AuditingEntityListener.class)
public class ClientEntity extends PersonEntity {

    @NotBlank(message = "{Contraseña no debe estar en blanco")
    @Size(max = 25, message = "{Tamaño maximo de 25 ")
    @Column(name = "password", nullable = false)
    private String password;

}
