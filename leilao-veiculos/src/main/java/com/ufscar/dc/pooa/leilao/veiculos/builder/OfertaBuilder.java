package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Endereco;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OfertaBuilder {
    public Oferta build(CreateOfertaDTO dto, Vendedor vendedor, Veiculo veiculo, Endereco endereco) {
        Oferta oferta = new Oferta();
        oferta.setDhInicio(dto.getDhInicio());
        oferta.setDhFim(dto.getDhFim());
        oferta.setDhCriacao(LocalDateTime.now());
        oferta.setValorInicial(dto.getValorInicial());
        oferta.setValorIncremental(dto.getValorIncremental());
        oferta.setEstado(dto.getEstado());
        oferta.setVendedor(vendedor);
        oferta.setVeiculo(veiculo);
        oferta.setEndereco(endereco);
        return oferta;
    }

    public ReturnOfertaDTO build(Oferta entity) {
        ReturnOfertaDTO ofertaDTO = new ReturnOfertaDTO();
        ofertaDTO.setId(entity.getId());
        ofertaDTO.setValorInicial(entity.getValorInicial());
        ofertaDTO.setValorIncremental(entity.getValorIncremental());
        ofertaDTO.setEstado(entity.getEstado());
        EnderecoBuilder enderecoBuilder = new EnderecoBuilder();
        ofertaDTO.setEndereco(enderecoBuilder.build(entity.getEndereco()));
        VendedorBuilder vendedorBuilder = new VendedorBuilder();
        ofertaDTO.setVendedor(vendedorBuilder.build(entity.getVendedor()));
        VeiculoBuilder veiculoBuilder = new VeiculoBuilder();
        ofertaDTO.setVeiculo(veiculoBuilder.build(entity.getVeiculo()));
        ofertaDTO.setDhInicio(entity.getDhInicio());
        ofertaDTO.setDhFim(entity.getDhFim());
        return ofertaDTO;
    }
}
