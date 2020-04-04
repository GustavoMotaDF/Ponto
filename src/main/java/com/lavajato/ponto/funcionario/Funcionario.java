/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavajato.ponto.funcionario;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author gustavo
 */
@Entity
//@SQLDelete(sql = "update Funcionario set ativo = 1 where idfuncionario = ?")
public class Funcionario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idfuncionario;
    private String nome;
    private String cpf;
    private Boolean ativo;
    
    @OneToMany(mappedBy = "idfuncionario")
    private List<Ponto> ponto;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idfuncionario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.idfuncionario, other.idfuncionario)) {
            return false;
        }
        return true;
    }

    public Long getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Long idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Ponto> getPonto() {
        return ponto;
    }

    public void setPonto(List<Ponto> ponto) {
        this.ponto = ponto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    

    public Funcionario() {
    }

    public Funcionario(Long idfuncionario, String nome, String cpf, List<Ponto> ponto, Boolean ativo) {
        this.idfuncionario = idfuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.ponto = ponto;
        this.ativo = ativo;
    }
    
    
}
