package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CompradorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.builder.EnderecoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.builder.OfertaBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.OfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.TipoVeiculo;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;
import com.ufscar.dc.pooa.leilao.veiculos.repository.OfertaRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.OfertaService;
import com.ufscar.dc.pooa.leilao.veiculos.service.VeiculoService;
import com.ufscar.dc.pooa.leilao.veiculos.service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OfertaServiceImpl implements OfertaService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final VendedorService vendedorService;
    private final VeiculoService veiculoService;
    private final EnderecoBuilder enderecoBuilder;
    private final OfertaBuilder builder;
    private final OfertaRepository repository;

    @Override
    public void create(OfertaDTO dto) {
        log.debug("Criando nova oferta: {}", dto);
        validate(dto);
        Vendedor vendedor = vendedorService.findDomainById(dto.getIdVendedor());
        Veiculo veiculo = veiculoService.findDomainById(dto.getIdVeiculo());
        repository.save(builder.build(dto, vendedor, veiculo, enderecoBuilder.build(dto.getEndereco())));
    }

    private static void validate(OfertaDTO dto) {
        Optional.ofNullable(dto.getDhInicio()).orElseThrow(() -> new BadRequestException("campo dhInicio é obrigatório"));
        Optional.ofNullable(dto.getDhFim()).orElseThrow(() -> new BadRequestException("campo dhFim é obrigatório"));
        Optional.ofNullable(dto.getValorInicial()).orElseThrow(() -> new BadRequestException("campo valorInicial é obrigatório"));
        Optional.ofNullable(dto.getValorIncremental()).orElseThrow(() -> new BadRequestException("campo valorIncremental é obrigatório"));
        Optional.ofNullable(dto.getEstado()).orElseThrow(() -> new BadRequestException("campo estado é obrigatório"));
        Optional.ofNullable(dto.getIdVendedor()).orElseThrow(() -> new BadRequestException("campo idVendedor é obrigatório"));
        Optional.ofNullable(dto.getIdVeiculo()).orElseThrow(() -> new BadRequestException("campo idVeiculo é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getEstado()).orElseThrow(() -> new BadRequestException("campo endereco.estado é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getCidade()).orElseThrow(() -> new BadRequestException("campo endereco.cidade é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getCep()).orElseThrow(() -> new BadRequestException("campo endereco.cep é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getBairro()).orElseThrow(() -> new BadRequestException("campo endereco.bairro é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getRua()).orElseThrow(() -> new BadRequestException("campo endereco.rua é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getNumero()).orElseThrow(() -> new BadRequestException("campo endereco.numero é obrigatório"));
    }
}
