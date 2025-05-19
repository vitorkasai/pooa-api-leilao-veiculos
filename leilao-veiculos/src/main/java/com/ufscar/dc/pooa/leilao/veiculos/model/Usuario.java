package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_USUARIO")
public class Usuario {
    @Id
    @Column(name = "ID_USUARIO", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAPEL", nullable = false, length = 30)
    private String papel;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "SOBRENOME", nullable = false, length = 100)
    private String sobrenome;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "TELEFONE", length = 50)
    private String telefone;

    @Column(name = "DOCUMENTO", nullable = false, unique = true, length = 100)
    private String documento;

    @Column(name = "DH_CRIACAO", nullable = false)
    private LocalDateTime dhCriacao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private Set<Notificacao> notificacaoList;
}