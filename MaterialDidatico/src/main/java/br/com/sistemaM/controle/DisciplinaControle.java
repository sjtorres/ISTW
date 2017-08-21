/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.controle;

import br.com.sistemaM.entidade.Disciplina;
import br.com.sistemaM.entidade.ItemDisciplina;
import br.com.sistemaM.enums.NivelAcesso;
import br.com.sistemaM.facade.AbstractFacade;
import br.com.sistemaM.facade.DisciplinaFacade;
import br.com.sistemaM.facade.ItemDisciplinaFacade;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private DisciplinaFacade disciplinaFacade;
    @Inject
    private ItemDisciplinaFacade itemDisciplinaFacade;
    private Disciplina disciplina;
    @Inject
    private CursoControle cursoControle;
    @Inject
    private LoginControle loginControle;
    private ItemDisciplina itemDisciplina = new ItemDisciplina();
    private String codAcesso;

    public DisciplinaControle() {
        super(Disciplina.class);
    }

    @Override
    public AbstractFacade<Disciplina> getFacade() {
        return disciplinaFacade;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String salvar() {
        try {
            if (loginControle.getUsuario().getNivelAcesso().equals(NivelAcesso.ALUNO)) {
                ItemDisciplina it = new ItemDisciplina();
                it.setDisciplina(disciplina);
                it.setUsuario(loginControle.getUsuario());
                itemDisciplinaFacade.salvar(it);
                mensagem("Salvo com sucesso: ", FacesMessage.SEVERITY_INFO);
                voltar();
            } else {
                return super.salvar();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String cadastroCurso() {
        cursoControle.alterar();
        return "/app/curso/list?faces-redirect=true";
    }

    public void addItem() {
        try {
            disciplina = disciplinaFacade.BuscarDisciplinaPeloCodAcesso(codAcesso);
            itemDisciplina.setDisciplina(disciplina);
            itemDisciplina.setUsuario(loginControle.getUsuario());
            super.getEntidade().addItem(itemDisciplina);
            itemDisciplina = new ItemDisciplina();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public ItemDisciplina getItemDisciplina() {
        return itemDisciplina;
    }

    public void setItemDisciplina(ItemDisciplina itemDisciplina) {
        this.itemDisciplina = itemDisciplina;
    }

    public String getCodAcesso() {
        return codAcesso;
    }

    public void setCodAcesso(String codAcesso) {
        this.codAcesso = codAcesso;
    }

}
