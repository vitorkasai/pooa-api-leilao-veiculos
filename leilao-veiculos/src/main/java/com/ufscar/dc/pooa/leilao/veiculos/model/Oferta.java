package com.ufscar.dc.pooa.leilao.veiculos.model;

import java.time.LocalDateTime;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_OFERTA")
public class Oferta {
    @Id
    @Column(name = "ID_OFERTA", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DH_CRIACAO", nullable = false)
    private LocalDateTime dhCriacao;

    @Column(name = "DH_INICIO", nullable = false)
    private LocalDateTime dhInicio;

    @Column(name = "DH_FIM", nullable = false)
    private LocalDateTime dhFim;

    @OneToOne
    @JoinColumn(name = "ID_VEICULO", nullable = false, unique = true)
    private Veiculo veiculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ENDERECO", nullable = false, unique = true)
    private Endereco endereco;

    @Column(name = "VALOR_INICIAL", nullable = false)
    private double valorInicial;

    @Column(name = "VALOR_INCREMENTAL", nullable = false)
    private double valorIncremental;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Vendedor vendedor;
}