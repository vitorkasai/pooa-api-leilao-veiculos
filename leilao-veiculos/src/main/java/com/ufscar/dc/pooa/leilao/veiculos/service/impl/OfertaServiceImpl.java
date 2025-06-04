package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.EnderecoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.builder.OfertaBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.OfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;
import com.ufscar.dc.pooa.leilao.veiculos.repository.OfertaRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.NotificacaoService;
import com.ufscar.dc.pooa.leilao.veiculos.service.OfertaService;
import com.ufscar.dc.pooa.leilao.veiculos.service.VeiculoService;
import com.ufscar.dc.pooa.leilao.veiculos.service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private NotificacaoService notificacaoService;

    @Override
    public Oferta findDomainById(Long id) {
        log.debug("Buscando oferta: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar oferta de id: " + id));
    }

    @Override
    public void create(OfertaDTO dto) {
        log.debug("Criando nova oferta: {}", dto);
        validate(dto);
        Vendedor vendedor = vendedorService.findDomainById(dto.getIdVendedor());
        Veiculo veiculo = veiculoService.findDomainById(dto.getIdVeiculo());
        LocalDateTime now = LocalDateTime.now();
        if (dto.getDhInicio().isBefore(now)) {
            log.error("O dhInicio {} deve ser mais velha que data atual {}", dto.getDhInicio(), now);
            throw new BadRequestException("O dhInicio deve ser mais velha que data atual");
        }
        if (dto.getDhInicio().isAfter(dto.getDhFim())) {
            log.error("O dhFim {} deve ser mais velha que dhInicio {}", dto.getDhFim(), dto.getDhInicio());
            throw new BadRequestException("O dhFim deve ser mais velha que dhInicio");
        }
        dto.setEstado(Estado.NAO_INICIADO);
        repository.save(builder.build(dto, vendedor, veiculo, enderecoBuilder.build(dto.getEndereco())));
        
        setNotificacaoTipo(new NotificacaoLanceRecebido());
        notificacaoService.createNotificacao();
    }
    
    private void setNotificacaoTipo(NotificacaoService notificacaoTipo) {
    	this.notificacaoService = notificacaoTipo;
    }

    private static void validate(OfertaDTO dto) {
        Optional.ofNullable(dto.getDhInicio()).orElseThrow(() -> new BadRequestException("Campo dhInicio é obrigatório"));
        Optional.ofNullable(dto.getDhFim()).orElseThrow(() -> new BadRequestException("Campo dhFim é obrigatório"));
        Optional.ofNullable(dto.getValorInicial()).orElseThrow(() -> new BadRequestException("Campo valorInicial é obrigatório"));
        Optional.ofNullable(dto.getValorIncremental()).orElseThrow(() -> new BadRequestException("Campo valorIncremental é obrigatório"));
        Optional.ofNullable(dto.getIdVendedor()).orElseThrow(() -> new BadRequestException("Campo idVendedor é obrigatório"));
        Optional.ofNullable(dto.getIdVeiculo()).orElseThrow(() -> new BadRequestException("Campo idVeiculo é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getEstado()).orElseThrow(() -> new BadRequestException("Campo endereco.estado é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getCidade()).orElseThrow(() -> new BadRequestException("Campo endereco.cidade é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getCep()).orElseThrow(() -> new BadRequestException("Campo endereco.cep é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getBairro()).orElseThrow(() -> new BadRequestException("Campo endereco.bairro é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getRua()).orElseThrow(() -> new BadRequestException("Campo endereco.rua é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getNumero()).orElseThrow(() -> new BadRequestException("Campo endereco.numero é obrigatório"));
    }
}
