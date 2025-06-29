package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(schema = "LEILAO_VEICULOS", name = "TB_VEICULO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "TIPO_VEICULO",
        discriminatorType = DiscriminatorType.STRING
)
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

    protected Veiculo(String modelo, LocalDateTime dhCriacao, String placa, String cor, String renavam, int quilometragem, String descricao) {
        this.modelo = modelo;
        this.dhCriacao = dhCriacao;
        this.placa = placa;
        this.cor = cor;
        this.renavam = renavam;
        this.quilometragem = quilometragem;
        this.descricao = descricao;
    }
}
