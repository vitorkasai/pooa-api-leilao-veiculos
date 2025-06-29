package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comprador extends Usuario {
    @Column(name = "INTERESSE", length = 150)
    private String interesse;

    @Column(name = "DATA_NASCIMENTO", length = 15)
    private String dataNascimento;
}