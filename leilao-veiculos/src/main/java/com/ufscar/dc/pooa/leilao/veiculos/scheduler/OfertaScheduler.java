package com.ufscar.dc.pooa.leilao.veiculos.scheduler;

import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import com.ufscar.dc.pooa.leilao.veiculos.repository.OfertaRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CreateNotificacaoService;
import com.ufscar.dc.pooa.leilao.veiculos.service.LanceService;
import com.ufscar.dc.pooa.leilao.veiculos.service.impl.VeiculoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class OfertaScheduler {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final OfertaRepository repository;
    private final LanceService lanceService;
    private final Map<String, CreateNotificacaoService> notificacaoStrategy;

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void syncEstados() {
        log.info("Iniciando sincronização dos estados das ofertas");
        List<Oferta> ofertas = repository.findAll();
        ofertas.forEach(oferta -> {
            Estado estadoAnterior = oferta.getEstado();
            Estado novoEstado = calculateEstado(oferta);
            if (estadoAnterior != Estado.CANCELADO && estadoAnterior != novoEstado) {
                oferta.setEstado(novoEstado);
                log.info("Oferta {} sincronizada de {} para {}", oferta.getId(), estadoAnterior, novoEstado);
                repository.save(oferta);
            }
        });
        log.info("Sincronização dos estados das ofertas concluída");
    }

    private Estado calculateEstado(Oferta oferta) {
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isBefore(oferta.getDhInicio())) {
            return Estado.NAO_INICIADO;
        } else if (!agora.isAfter(oferta.getDhFim())) {
            return Estado.EM_ANDAMENTO;
        } else {
        	Lance ultimoLance = lanceService.findUltimoLance(oferta.getId());
            CreateNotificacaoService notificacaoService = notificacaoStrategy.get("lanceArrematado");
            notificacaoService.createNotificacao(ultimoLance);
            return Estado.FINALIZADO;
        }
    }
}
