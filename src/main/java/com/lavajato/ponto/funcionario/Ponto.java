/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavajato.ponto.funcionario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author gustavo
 */
@Entity
public class Ponto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idponto;
    private LocalDateTime datahora; 
    private String tipo;
    
    @ManyToOne
    @JoinColumn(name="idfuncionario")
    private Funcionario idfuncionario;

    public Long getIdponto() {
        return idponto;
    }

    public void setIdponto(Long idponto) {
        this.idponto = idponto;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }

    public Funcionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Funcionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idponto);
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
        final Ponto other = (Ponto) obj;
        if (!Objects.equals(this.idponto, other.idponto)) {
            return false;
        }
        return true;
    }

    public Ponto() {
    }

    public Ponto(Long idponto, LocalDateTime datahora, Funcionario idfuncionario, String tipo) {
        this.idponto = idponto;
        this.datahora = datahora;
        this.idfuncionario = idfuncionario;
        this.tipo = tipo;
    }
    
    
    
}
