/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.facade;

import br.com.sistemaM.entidade.Curso;
import br.com.sistemaM.persistencia.Transacional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tiago
 */
@Transacional
public class CursoFacade extends AbstractFacade<Curso> implements Serializable{
    
    @Inject
    private EntityManager em;

    public CursoFacade() {
        super(Curso.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
    
    public List<Curso> listarProfessor(String login) {
        Query q = em.createQuery("FROM curso AS c");
//        Query q = em.createQuery("FROM Disciplina AS d INNER JOIN d.curso AS c WHERE d.usuario.login = '" + login + "'");
        return q.getResultList();
    }
    
    public List<Curso> listarAluno(String login) {
        Query q = em.createQuery("FROM curso AS c");
//        Query q = em.createQuery("FROM Usuario AS u INNER JOIN u.itensDisciplina AS i INNER JOIN i.disciplina AS d INNER JOIN d.curso AS c WHERE i.usuario.login = '" + login + "'");
        return q.getResultList();
    }
      
}
