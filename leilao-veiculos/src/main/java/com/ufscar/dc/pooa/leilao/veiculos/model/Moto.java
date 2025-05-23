package com.ufscar.dc.pooa.leilao.veiculos.model;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Partida;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MOTO")
@Getter
@Setter
public class Moto extends Veiculo{
    @Column(name = "CILINDRADA")
    private int cilindrada;

    @Column(name = "MARCHA")
    private int marcha;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARTIDA")
    private Partida partida;

    public Moto(String modelo, LocalDateTime dhCriacao, String placa, String cor, String renavam, int quilometragem, String descricao, int cilindrada, int marcha, Partida partida) {
        super(modelo, dhCriacao, placa, cor, renavam, quilometragem, descricao);
        this.cilindrada = cilindrada;
        this.marcha = marcha;
        this.partida = partida;
    }
}
