package br.com.fiap.contatos.model;

import br.com.fiap.contatos.enums.UsuarioRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "TBL_USUARIOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USUARIOS"
    )
    @SequenceGenerator(
            name = "SEQ_USUARIOS",
            sequenceName = "SEQ_USUARIOS",
            allocationSize = 1
    )
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private UsuarioRole role;
}
