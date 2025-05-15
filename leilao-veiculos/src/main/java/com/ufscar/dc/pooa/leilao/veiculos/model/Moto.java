package com.ufscar.dc.pooa.leilao.veiculos.model;

import com.ufscar.dc.pooa.leilao.veiculos.model.enums.Partida;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MOTO")
public class Moto extends Veiculo{
    @Column(name = "CILINDRADA", nullable = false)
    private int cilindrada;

    @Column(name = "MARCHA", nullable = false)
    private int marcha;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARTIDA", nullable = false)
    private Partida partida;

    @OneToOne
    @JoinColumn(name = "ID_VEICULO")
    private Veiculo veiculo;

    public Moto(String modelo, LocalDateTime dhCriacao, String placa, String cor, String renavam, int quilometragem, String descricao, int cilindrada, int marcha, Partida partida) {
        super(modelo, dhCriacao, placa, cor, renavam, quilometragem, descricao);
        this.cilindrada = cilindrada;
        this.marcha = marcha;
        this.partida = partida;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getMarcha() {
        return marcha;
    }

    public void setMarcha(int marcha) {
        this.marcha = marcha;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
