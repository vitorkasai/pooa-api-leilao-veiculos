package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor extends Usuario {
    @Column(name = "NOME_FANTASIA", length = 100)
    private String nomeFantasia;

    @Column(name = "CONTA_BANCARIA", length = 100)
    private String contaBancaria;
}