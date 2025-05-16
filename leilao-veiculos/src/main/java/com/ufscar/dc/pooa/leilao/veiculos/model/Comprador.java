package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_USUARIO")
public class Comprador extends Usuario {
    @Column(name = "INTERESSE", nullable = false, length = 150)
    private String interesse;

    @Column(name = "DATA_NASCIMENTO", nullable = false, length = 15)
    private String dataNascimento;
}