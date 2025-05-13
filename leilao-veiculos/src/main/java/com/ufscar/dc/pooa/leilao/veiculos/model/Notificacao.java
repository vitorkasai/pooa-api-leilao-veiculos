package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_NOTIFICACAO")
public class Notificacao {
    @Id
    @Column(name = "ID_NOTIFICACAO", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTEUDO", nullable = false, length = 255)
    private String conteudo;

    @Column(name = "IN_VISUALIZADO", nullable = false)
    private boolean inVisualizado;

    @Column(name = "DH_CRIACAO", nullable = false)
    private LocalDateTime dhCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public boolean isInVisualizado() {
        return inVisualizado;
    }

    public void setInVisualizado(boolean inVisualizado) {
        this.inVisualizado = inVisualizado;
    }

    public LocalDateTime getDhCriacao() {
        return dhCriacao;
    }

    public void setDhCriacao(LocalDateTime dhCriacao) {
        this.dhCriacao = dhCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
