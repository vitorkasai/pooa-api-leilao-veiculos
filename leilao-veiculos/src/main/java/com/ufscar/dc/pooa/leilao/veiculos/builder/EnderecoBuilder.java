package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateEnderecoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Endereco;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EnderecoBuilder {
    public Endereco build(CreateEnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setCep(dto.getCep());
        endereco.setBairro(dto.getBairro());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setDhCriacao(LocalDateTime.now());
        return endereco;
    }
}
