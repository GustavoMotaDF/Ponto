/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavajato.ponto.BO;

import com.lavajato.ponto.funcionario.Funcionario;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gustavo
 */
public class FuncionarioBO {
    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("ponto");
    
    public List<Funcionario> getFuncionarios() throws Exception{
        List<Funcionario>funcionarios;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        funcionarios = em.createQuery("from Funcionario where ativo = 1").getResultList();
        em.getTransaction().commit();
        em.clear();
        em.close();
        
        if(funcionarios==null || funcionarios.isEmpty() || funcionarios.equals("") ){
            throw new Exception("Sem Funcionarios cadastrados!");            
        }
        
        return funcionarios;
    }
    
     public void IncluirFuncionario (String nome, String cpf) throws Exception{ 
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(nome==null || nome.isEmpty() || nome.equals("") ){
            throw new Exception("Não foi informado o nome do Funcionario!"); 
            
        }
        if(cpf==null || cpf.isEmpty() || cpf.equals("") ){
            throw new Exception("Não foi informado o cpf do Funcionario!");            
        }
        
        Funcionario funcionarios = new Funcionario();
        funcionarios.setNome(nome);
        funcionarios.setCpf(cpf);
        funcionarios.setAtivo(Boolean.TRUE);
     
        
        em.persist(funcionarios);
        em.getTransaction().commit();
        
        em.clear();
        em.close();
         
        
    }
     public void EditarFuncionario (String idfuncionario, String nome, String cpf) throws Exception{
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Funcionario funcionarios = em.find(Funcionario.class, Long.valueOf(idfuncionario));
        funcionarios.setNome(nome);
        funcionarios.setCpf(cpf);
        
        em.merge(funcionarios);
        em.getTransaction().commit();        
        
        
        if(idfuncionario==null || idfuncionario.isEmpty() || idfuncionario.equals("") ){
            throw new Exception("Não foi informado o id do Funcionario!");            
        }
        if(nome==null || nome.isEmpty() || nome.equals("") ){
            throw new Exception("Não foi informado o nome do Funcionario!");            
        }
        if(cpf==null || cpf.isEmpty() || cpf.equals("") ){
            throw new Exception("Não foi informado o cpf do Funcionario!");            
        }
        em.clear();
        em.close();
        
    }
     
     public Boolean ExcluirFuncionario(String idfuncionario)throws Exception{
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Funcionario funcionario = em.getReference(Funcionario.class, Long.valueOf(idfuncionario));
        em.remove(funcionario);
        
        em.getTransaction().commit();
       
        
        if(idfuncionario==null || idfuncionario.isEmpty() || idfuncionario.equals("") ){
            throw new Exception("Erro ao desativar Funcionario, id não foi informado");            
        }
        em.clear();
        em.close();
        return true;
        
    }
    public Funcionario getFuncionario(String idfuncionario)throws Exception{
        Funcionario funcionario;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        funcionario = em.find(Funcionario.class,Long.valueOf(idfuncionario));
        em.getTransaction().commit();
        em.clear();
        em.close();
        
        if(idfuncionario==null || idfuncionario.isEmpty() || idfuncionario.equals("") ){
            throw new Exception("Erro ao pesquisar funcionario, id não foi informado");            
        }
        
        return funcionario;
    }
}
