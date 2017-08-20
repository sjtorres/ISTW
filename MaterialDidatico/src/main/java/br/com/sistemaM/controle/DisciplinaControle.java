/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.controle;

import br.com.sistemaM.entidade.Disciplina;
import br.com.sistemaM.facade.AbstractFacade;
import br.com.sistemaM.facade.DisciplinaFacade;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tiago
 */
@Named
@ViewScoped
public class DisciplinaControle extends AbstractControle<Disciplina> implements Serializable {

    @Inject
    private DisciplinaFacade habilidadeFacade;
    private Disciplina habilidade;
    @Inject
    private CursoControle cursoControle;

    public DisciplinaControle() {
        super(Disciplina.class);
    }

    @Override
    public AbstractFacade<Disciplina> getFacade() {
        return habilidadeFacade;
    }
    
    public Disciplina getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Disciplina habilidade) {
        this.habilidade = habilidade;
    }
        
    public String cadastroCurso() {
        cursoControle.alterar();
        return "/app/curso/list?faces-redirect=true";
    }

}
