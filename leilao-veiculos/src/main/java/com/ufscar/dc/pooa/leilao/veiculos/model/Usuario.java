package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "LEILAO_VEICULOS", name = "TB_USUARIO")
public class Usuario {
    @Id
    @Column(name = "ID_USUARIO", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAPEL", nullable = false, length = 30)
    private String papel;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "SOBRENOME", nullable = false, length = 100)
    private String sobrenome;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "TELEFONE", length = 50)
    private String telefone;

    @Column(name = "DOCUMENTO", nullable = false, unique = true, length = 100)
    private String documento;

    @Column(name = "DH_CRIACAO", nullable = false)
    private LocalDateTime dhCriacao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private Set<Notificacao> notificacaoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public LocalDateTime getDhCriacao() {
        return dhCriacao;
    }

    public void setDhCriacao(LocalDateTime dhCriacao) {
        this.dhCriacao = dhCriacao;
    }

    public Set<Notificacao> getNotificacaoList() {
        return notificacaoList;
    }

    public void setNotificacaoList(Set<Notificacao> notificacaoList) {
        this.notificacaoList = notificacaoList;
    }
}