package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;
import com.ufscar.dc.pooa.leilao.veiculos.repository.VendedorRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VendedorServiceImpl implements VendedorService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorServiceImpl.class);
    private final VendedorRepository repository;;

    @Override
    public Vendedor findDomainById(Long id) {
        log.debug("Buscando vendedor: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar vendedor de id: " + id));
    }
}
