package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.VeiculoFactory;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import org.springframework.stereotype.Component;


@Component
public class VeiculoBuilder {

    public Veiculo build(VeiculoDTO dto) {
        String tipo = dto.getTipoVeiculo();

        if ("CARRO".equalsIgnoreCase(tipo)) {
            return VeiculoFactory.criarCarro(dto);
        } else if ("MOTO".equalsIgnoreCase(tipo)) {
            return VeiculoFactory.criarMoto(dto);
        }

        throw new NotFoundException( "Tipo de veículo inválido: " + tipo);
    }
}

