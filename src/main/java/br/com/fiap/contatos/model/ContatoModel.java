package br.com.fiap.contatos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TBL_CONTATOS_PROJECT")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ContatoModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TBL_CONTATOS_PROJECT_SEQ"
    )
    @SequenceGenerator(
        name = "TTBL_CONTATOS_PROJECT_SEQ",
        sequenceName = "TBL_CONTATOS_PROJECT_SEQ",
            allocationSize = 1
    )
    private Long id;
    private String nome;
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    public Long getId() {
        return id;
    }
}
