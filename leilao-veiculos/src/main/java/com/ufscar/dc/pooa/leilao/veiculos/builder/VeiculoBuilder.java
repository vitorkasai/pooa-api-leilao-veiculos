package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnVeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Carro;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import org.springframework.stereotype.Component;

@Component
public class VeiculoBuilder {
    public ReturnVeiculoDTO build(Veiculo entity) {
        ReturnVeiculoDTO veiculoDTO = new ReturnVeiculoDTO();
        veiculoDTO.setTipoVeiculo(entity instanceof Carro ? "CARRO" : "MOTO");
        veiculoDTO.setModelo(entity.getModelo());
        veiculoDTO.setPlaca(entity.getPlaca());
        veiculoDTO.setCor(entity.getCor());
        veiculoDTO.setRenavam(entity.getRenavam());
        veiculoDTO.setQuilometragem(entity.getQuilometragem());
        veiculoDTO.setDescricao(entity.getDescricao());
        return veiculoDTO;
    }
}
