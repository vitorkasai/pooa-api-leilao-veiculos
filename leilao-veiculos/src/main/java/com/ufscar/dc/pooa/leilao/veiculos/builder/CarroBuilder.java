package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.VeiculoFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Cambio;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Direcao;
import com.ufscar.dc.pooa.leilao.veiculos.model.Carro;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CarroBuilder {

    public Veiculo build(VeiculoDTO dto) {
        Carro carro = VeiculoFactory.criaCarro();
        carro.setModelo(dto.getModelo());
        carro.setDhCriacao(LocalDateTime.now());
        carro.setPlaca(dto.getPlaca());
        carro.setCor(dto.getCor());
        carro.setRenavam(dto.getRenavam());
        carro.setQuilometragem(dto.getQuilometragem());
        carro.setDescricao(dto.getDescricao());
        carro.setNumeroPortas(dto.getNumeroPortas());
        carro.setDirecao(Direcao.valueOf(dto.getDirecao().toUpperCase()));
        carro.setCambio(Cambio.valueOf(dto.getCambio().toUpperCase()));
        return carro;
    }
}