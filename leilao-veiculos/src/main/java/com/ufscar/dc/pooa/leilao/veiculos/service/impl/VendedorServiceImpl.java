package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.VendedorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVendedorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.encryption.EncryptionService;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Usuario;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;
import com.ufscar.dc.pooa.leilao.veiculos.repository.VendedorRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.UsuarioService;
import com.ufscar.dc.pooa.leilao.veiculos.service.VendedorService;
import com.ufscar.dc.pooa.leilao.veiculos.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VendedorServiceImpl implements VendedorService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorServiceImpl.class);
    private final UsuarioService usuarioService;
    private final VendedorRepository repository;
    private final VendedorBuilder builder;
    private final EncryptionService encryptionService;

    @Override
    public Vendedor findDomainById(Long id) {
        log.debug("Buscando vendedor: {}", id);
        Vendedor vendedor = repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar vendedor de id: " + id));

        vendedor.setDocumento(encryptionService.decrypt(vendedor.getDocumento()));
        vendedor.setContaBancaria(encryptionService.decrypt(vendedor.getContaBancaria()));

        return vendedor;
    }
    
    @Override
    public void create(CreateVendedorDTO dto) {
    	log.debug("Criando novo vendedor: {}", dto);
    	ValidatorUtil.validate(dto);
        usuarioService.findOptDomainByEmail(dto.getEmail()).ifPresent((Usuario usuario) -> {
            throw new BadRequestException("Falha ao criar vendedor, já existe um com o email " + dto.getEmail());
        });
        usuarioService.findOptDomainByDocumento(dto.getEmail()).ifPresent((Usuario usuario) -> {
            throw new BadRequestException("Falha ao criar vendedor, já existe um com o documento " + dto.getDocumento());
        });
    	repository.save(builder.build(dto));
    }
}