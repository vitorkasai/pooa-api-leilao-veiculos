package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_USUARIO")
public class Vendedor extends Usuario{
    @Column(name = "NOME_FANTASIA", nullable = false, length = 100)
    private String nomeFantasia;

    @Column(name = "CONTA_BANCARIA", nullable = false, length = 100)
    private String contaBancaria;
}