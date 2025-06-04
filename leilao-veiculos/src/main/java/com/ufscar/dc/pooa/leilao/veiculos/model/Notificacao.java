package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_NOTIFICACAO")
public class Notificacao {
    @Id
    @Column(name = "ID_NOTIFICACAO", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTEUDO", nullable = false, length = 255)
    private String conteudo;

    @Column(name = "IS_VISUALIZADO", nullable = false)
    private boolean isVisualizado;

    @Column(name = "DH_CRIACAO", nullable = false)
    private LocalDateTime dhCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
}