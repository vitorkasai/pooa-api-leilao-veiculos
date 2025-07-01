package com.ufscar.dc.pooa.leilao.veiculos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "LEILAO_VEICULOS", name = "TB_VEICULO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_VEICULO", discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Veiculo {
    @Id
    @Column(name = "ID_VEICULO", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MODELO", nullable = false, length = 100)
    private String modelo;

    @Column(name = "DH_CRIACAO", nullable = false)
    private LocalDateTime dhCriacao;

    @Column(name = "PLACA", nullable = false, length = 10)
    private String placa;

    @Column(name = "RENAVAM", nullable = false, length = 20)
    private String renavam;

    @Column(name = "COR", nullable = false, length = 20)
    private String cor;

    @Column(name = "QUILOMETRAGEM", nullable = false)
    private int quilometragem;

    @Column(name = "DESCRICAO", length = 255)
    private String descricao;

}
