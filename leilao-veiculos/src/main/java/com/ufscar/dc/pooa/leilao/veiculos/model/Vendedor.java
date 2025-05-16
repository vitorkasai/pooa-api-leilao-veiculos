package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor extends Usuario{
    @Column(name = "NOME_FANTASIA", nullable = false, length = 100)
    private String nomeFantasia;

    @Column(name = "CONTA_BANCARIA", nullable = false, length = 100)
    private String contaBancaria;
}