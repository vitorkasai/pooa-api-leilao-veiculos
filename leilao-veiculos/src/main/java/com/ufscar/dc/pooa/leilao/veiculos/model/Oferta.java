package com.ufscar.dc.pooa.leilao.veiculos.model;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "oferta")
    private Set<Lance> lanceList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Vendedor vendedor;
}