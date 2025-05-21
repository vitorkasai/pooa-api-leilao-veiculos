package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_ENDERECO")
public class Endereco {
    @Id
    @Column(name = "ID_ENDERECO", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DH_CRIACAO", nullable = false)
    private LocalDateTime dhCriacao;

    @Column(name = "ESTADO", nullable = false, length = 2)
    private String estado;

    @Column(name = "CIDADE", nullable = false, length = 100)
    private String cidade;

    @Column(name = "CEP", nullable = false, length = 9)
    private String cep;

    @Column(name = "BAIRRO", nullable = false, length = 100)
    private String bairro;

    @Column(name = "RUA", nullable = false, length = 150)
    private String rua;

    @Column(name = "NUMERO", nullable = false, length = 15)
    private String numero;
}