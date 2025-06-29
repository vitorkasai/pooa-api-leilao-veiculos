package com.ufscar.dc.pooa.leilao.veiculos.util;

import java.util.Optional;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateCompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateEnderecoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVendedorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.TipoVeiculo;

public class ValidatorUtil {
	public static void validate(CreateLanceDTO dto) {
		Optional.ofNullable(dto.getValor()).orElseThrow(() -> new BadRequestException("Campo valor é obrigatório"));
		Optional.ofNullable(dto.getCompradorId())
				.orElseThrow(() -> new BadRequestException("Campo compradorId é obrigatório"));
		Optional.ofNullable(dto.getOfertaId())
				.orElseThrow(() -> new BadRequestException("Campo ofertaId é obrigatório"));
	}

	public static void validate(CreateOfertaDTO dto) {
		Optional.ofNullable(dto.getDhInicio())
				.orElseThrow(() -> new BadRequestException("Campo dhInicio é obrigatório"));
		Optional.ofNullable(dto.getDhFim()).orElseThrow(() -> new BadRequestException("Campo dhFim é obrigatório"));
		Optional.ofNullable(dto.getValorInicial())
				.orElseThrow(() -> new BadRequestException("Campo valorInicial é obrigatório"));
		Optional.ofNullable(dto.getValorIncremental())
				.orElseThrow(() -> new BadRequestException("Campo valorIncremental é obrigatório"));
		Optional.ofNullable(dto.getVendedorId())
				.orElseThrow(() -> new BadRequestException("Campo vendedorId é obrigatório"));

		boolean veiculoIdPresente = dto.getVeiculoId() != null;
		boolean veiculoDtoPresente = dto.getVeiculo() != null;
		if (veiculoIdPresente == veiculoDtoPresente) {
			throw new BadRequestException("É necessário informar apenas o ID de um veículo existente ou apenas os dados para criar um novo veículo");
		}
		if (veiculoDtoPresente) {
			validate(dto.getVeiculo());
		}

		validate(dto.getEndereco());
	}

	public static void validate(CreateVeiculoDTO dto) {
		Optional.ofNullable(dto.getModelo()).orElseThrow(() -> new BadRequestException("Campo modelo é obrigatório"));
		Optional.ofNullable(dto.getPlaca()).orElseThrow(() -> new BadRequestException("Campo placa é obrigatório"));
		Optional.ofNullable(dto.getCor()).orElseThrow(() -> new BadRequestException("Campo cor é obrigatório"));
		Optional.ofNullable(dto.getRenavam()).orElseThrow(() -> new BadRequestException("Campo renavam é obrigatório"));
		Optional.ofNullable(dto.getTipoVeiculo())
				.orElseThrow(() -> new BadRequestException("Campo tipoVeiculo é obrigatório"));

		String tipo = dto.getTipoVeiculo();
		if (TipoVeiculo.CARRO.name().equalsIgnoreCase(tipo)) {
			Optional.ofNullable(dto.getNumeroPortas())
					.orElseThrow(() -> new BadRequestException("Campo numeroPortas é obrigatório para CARRO"));
			Optional.ofNullable(dto.getDirecao())
					.orElseThrow(() -> new BadRequestException("Campo direcao é obrigatório para CARRO"));
			Optional.ofNullable(dto.getCambio())
					.orElseThrow(() -> new BadRequestException("Campo cambio é obrigatório para CARRO"));
		} else if (TipoVeiculo.MOTO.name().equalsIgnoreCase(tipo)) {
			Optional.ofNullable(dto.getCilindrada())
					.orElseThrow(() -> new BadRequestException("Campo cilindrada é obrigatório para MOTO"));
			Optional.ofNullable(dto.getMarcha())
					.orElseThrow(() -> new BadRequestException("Campo marcha é obrigatório para MOTO"));
			Optional.ofNullable(dto.getPartida())
					.orElseThrow(() -> new BadRequestException("Campo partida é obrigatório para MOTO"));
		} else {
			throw new BadRequestException("Tipo de veículo inválido: " + tipo);
		}
	}

	public static void validate(CreateEnderecoDTO dto) {
		Optional.ofNullable(dto.getEstado())
				.orElseThrow(() -> new BadRequestException("Campo endereco.estado é obrigatório"));
		Optional.ofNullable(dto.getCidade())
				.orElseThrow(() -> new BadRequestException("Campo endereco.cidade é obrigatório"));
		Optional.ofNullable(dto.getCep())
				.orElseThrow(() -> new BadRequestException("Campo endereco.cep é obrigatório"));
		Optional.ofNullable(dto.getBairro())
				.orElseThrow(() -> new BadRequestException("Campo endereco.bairro é obrigatório"));
		Optional.ofNullable(dto.getRua())
				.orElseThrow(() -> new BadRequestException("Campo endereco.rua é obrigatório"));
		Optional.ofNullable(dto.getNumero())
				.orElseThrow(() -> new BadRequestException("Campo endereco.numero é obrigatório"));
	}

	public static void validate(CreateCompradorDTO dto) {
		validatePessoa(dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getTelefone(), dto.getDocumento());
		Optional.ofNullable(dto.getInteresse())
				.orElseThrow(() -> new BadRequestException("Campo interesse é obrigatório"));
		Optional.ofNullable(dto.getDataNascimento())
				.orElseThrow(() -> new BadRequestException("Campo dataNascimento é obrigatório"));
	}

	public static void validate(CreateVendedorDTO dto) {
		validatePessoa(dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getTelefone(), dto.getDocumento());
		Optional.ofNullable(dto.getNomeFantasia())
				.orElseThrow(() -> new BadRequestException("Campo nomeFantasia é obrigatório"));
		Optional.ofNullable(dto.getContaBancaria())
				.orElseThrow(() -> new BadRequestException("Campo contaBancaria é obrigatório"));
	}

	private static void validatePessoa(String nome, String sobrenome, String email, String telefone, String documento) {
		Optional.ofNullable(nome).orElseThrow(() -> new BadRequestException("Campo nome é obrigatório"));
		Optional.ofNullable(sobrenome).orElseThrow(() -> new BadRequestException("Campo sobrenome é obrigatório"));
		Optional.ofNullable(email).orElseThrow(() -> new BadRequestException("Campo email é obrigatório"));
		Optional.ofNullable(telefone).orElseThrow(() -> new BadRequestException("Campo telefone é obrigatório"));
		Optional.ofNullable(documento).orElseThrow(() -> new BadRequestException("Campo documento é obrigatório"));
	}
}
