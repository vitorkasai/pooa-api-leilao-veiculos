package com.ufscar.dc.pooa.leilao.veiculos.model;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Cambio;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Direcao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {
    @Column(name = "NUMERO_PORTAS", nullable = false)
    private int numeroPortas;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIRECAO", nullable = false)
    private Direcao direcao;

    @Enumerated(EnumType.STRING)
    @Column(name = "CAMBIO", nullable = false)
    private Cambio cambio;

    @OneToOne
    @JoinColumn(name = "ID_VEICULO")
    private Veiculo veiculo;

    public Carro(String modelo, LocalDateTime dhCriacao, String placa, String cor, String renavam, int quilometragem, String descricao, int numeroPortas, Direcao direcao, Cambio cambio) {
        super(modelo, dhCriacao, placa, cor, renavam, quilometragem, descricao);
        this.numeroPortas = numeroPortas;
        this.direcao = direcao;
        this.cambio = cambio;
    }


}
