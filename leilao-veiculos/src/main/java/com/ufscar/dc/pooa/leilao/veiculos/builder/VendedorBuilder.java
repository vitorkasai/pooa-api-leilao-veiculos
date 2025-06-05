package com.ufscar.dc.pooa.leilao.veiculos.builder;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ufscar.dc.pooa.leilao.veiculos.dto.VendedorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.UsuarioPapel;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;

@Component
public class VendedorBuilder {
	public Vendedor build(VendedorDTO dto) {
		Vendedor vendedor = new Vendedor();
        vendedor.setPapel(UsuarioPapel.VENDEDOR.getValue());
        vendedor.setNome(dto.getNome());
        vendedor.setSobrenome(dto.getSobrenome());
        vendedor.setEmail(dto.getEmail());
        vendedor.setTelefone(dto.getTelefone());
        vendedor.setDocumento(dto.getDocumento());
        vendedor.setNomeFantasia(dto.getNomeFantasia());
        vendedor.setContaBancaria(dto.getContaBancaria());
        vendedor.setDhCriacao(LocalDateTime.now());
        return vendedor;
	}
}
