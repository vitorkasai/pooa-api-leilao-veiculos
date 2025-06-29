package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_LANCE")
public class Lance {
    @Id
    @Column(name = "ID_LANCE", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "DH_CRIACAO", nullable = false)
    private LocalDateTime dhCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OFERTA")
    private Oferta oferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Comprador comprador;
}