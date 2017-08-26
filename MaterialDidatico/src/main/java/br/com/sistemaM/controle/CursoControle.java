/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.controle;

import br.com.sistemaM.entidade.Curso;
import br.com.sistemaM.facade.AbstractFacade;
import br.com.sistemaM.facade.CursoFacade;
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
public class CursoControle extends AbstractControle<Curso> implements Serializable {

    @Inject
    private CursoFacade cursoFacade;
    private Curso curso;

    public CursoControle() {
        super(Curso.class);
    }

    @Override
    public AbstractFacade<Curso> getFacade() {
        return cursoFacade;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
