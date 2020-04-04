/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavajato.ponto.BO;

import com.lavajato.ponto.funcionario.Funcionario;
import com.lavajato.ponto.funcionario.Ponto;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gustavo
 */
public class PontoBO {
    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("ponto");
    public List<Ponto> getCaixa() throws Exception{
        List<Ponto>ponto;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ponto = em.createQuery("from Ponto").getResultList();
        em.getTransaction().commit();
        em.clear();
        em.close();
        
        if(ponto==null || ponto.isEmpty() || ponto.equals("") ){
            throw new Exception("Sem registros cadastrados!");            
        }
        
        return ponto;
    }
    public void RegistrarPonto (String cpf, String tipo) throws Exception{ 
        EntityManager em = emf.createEntityManager();
        if(cpf==null || cpf.isEmpty() || cpf.equals("") ){
            throw new Exception ("CPF não informado!"); 
            
        }
        if(tipo==null || tipo.isEmpty() || tipo.equals("") ){
            throw new Exception("Não foi informado o telefone do Servico!");            
        }
        
        em.getTransaction().begin();
        
        Ponto ponto = new Ponto();
        Funcionario funcionario = em.find(Funcionario.class, Long.valueOf(cpf));
        ponto.setIdfuncionario(funcionario);
        ponto.setTipo(tipo);
        ponto.setDatahora(LocalDateTime.now());
        em.persist(ponto);
        em.getTransaction().commit();
        
        em.clear();
        em.close();      
        
        
    }
    public List<Ponto> getRegistroCpfDataHora(String datahora, String cpf) throws Exception{
        List<Ponto>ponto;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ponto = em.createNativeQuery("SELECT fun.nome, fun.cpf, po.datahora, po.tipo FROM Ponto po join Funcionario fun on po.idfuncionario = fun.idfuncionario where DATE(po.datahora) = (:datahora) AND cpf=:cpf  order by po.datahora;").setParameter("datahora", datahora).setParameter("cpf", cpf).getResultList();
        em.getTransaction().commit();
        em.clear();
        em.close();
        
        if(ponto==null || ponto.isEmpty() || ponto.equals("") ){
            throw new Exception("Sem registros para o CPF/Periodo solicitado!");            
        }
        
        return ponto;
    }
    
}
