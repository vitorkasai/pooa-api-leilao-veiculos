package com.ufscar.dc.pooa.leilao.veiculos.builder;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVendedorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;

@Component
public class VendedorBuilder {
	public Vendedor build(CreateVendedorDTO dto) {
		Vendedor vendedor = new Vendedor();
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

    public CreateVendedorDTO build(Vendedor entity) {
        CreateVendedorDTO vendedorDTO = new CreateVendedorDTO();
        vendedorDTO.setNome(entity.getNome());
        vendedorDTO.setSobrenome(entity.getSobrenome());
        vendedorDTO.setEmail(entity.getEmail());
        vendedorDTO.setTelefone(entity.getTelefone());
        vendedorDTO.setDocumento(entity.getDocumento());
        vendedorDTO.setNomeFantasia(entity.getNomeFantasia());
        vendedorDTO.setContaBancaria(entity.getContaBancaria());
        return vendedorDTO;
    }
}
