/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.facade;

import br.com.sistemaM.entidade.Disciplina;
import br.com.sistemaM.persistencia.Transacional;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author tiago
 */
@Transacional
public class DisciplinaFacade extends AbstractFacade<Disciplina> implements Serializable{
    
    @Inject
    private EntityManager em;

    public DisciplinaFacade() {
        super(Disciplina.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
      
}
