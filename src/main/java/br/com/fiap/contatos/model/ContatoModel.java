package br.com.fiap.contatos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tab_contatos")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ContatoModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TAB_CONTATOS_SEQ"
    )
    @SequenceGenerator(
        name = "TAB_CONTATOS_SEQ",
        sequenceName = "TAB_CONTATOS_SEQ",
            allocationSize = 50
    )
    private Long id;
    private String nome;
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

}
