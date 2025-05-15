package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
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
    private int id;

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

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getDhCriacao() {
        return dhCriacao;
    }

    public void setDhCriacao(LocalDateTime dhCriacao) {
        this.dhCriacao = dhCriacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
