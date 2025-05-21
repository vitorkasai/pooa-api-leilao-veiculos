package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.EnderecoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.EnderecoFactory;
import com.ufscar.dc.pooa.leilao.veiculos.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoBuilder {
    public Endereco build(EnderecoDTO dto) {
        Endereco endereco = EnderecoFactory.criaEndereco();
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setCep(dto.getCep());
        endereco.setBairro(dto.getBairro());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        return endereco;
    }
}
