/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.controle;

import br.com.sistemaM.converter.ConverterGenerico;
import br.com.sistemaM.facade.AbstractFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author tiago
 * @param <T>
 */
public abstract class AbstractControle<T> implements Serializable {

    private Class<T> classe;
    private List<T> listagem;
    private T entidade;
    private Boolean layoutList = true;
    private Boolean layoutForm = false;
    private Boolean layoutView = false;
    private ConverterGenerico converterGenerico;

    public AbstractControle(Class<T> classe) {
        this.classe = classe;
    }

    public abstract AbstractFacade<T> getFacade();

    public void novo() {
        try {
            entidade = classe.newInstance();
            layoutList = false;
            layoutForm = true;
            layoutView = false;
        } catch (InstantiationException | IllegalAccessException ex) {
            mensagem("Erro ao instanciar " + ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public void alterar() {
        layoutList = false;
        layoutForm = true;
        layoutView = false;
    }

    public String salvar() {
        try {
            getFacade().salvar(entidade);
            mensagem("Salvo com sucesso: ", FacesMessage.SEVERITY_INFO);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            mensagem("Erro ao salvar: " + ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
        return null;
    }

    public String excluir() {
        try {
            getFacade().excluir(entidade);
            mensagem("Excluido com sucesso: ", FacesMessage.SEVERITY_INFO);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            mensagem("Erro ao excluir: " + ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
        return null;
    }

    public void view() {
        layoutList = false;
        layoutForm = false;
        layoutView = true;
    }

    public List<T> getListar() throws Exception {
        if (listagem == null) {
            return getFacade().listar();
        }
        return listagem;
    }

    public List<T> AutoComplete(Long id) {
        return getFacade().AutoComplete(id);
    }

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(getFacade());
        }
        return converterGenerico;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sucesso!", "Celula Editada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Edit Cancelado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celula Editada", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    protected void mensagem(String msg, FacesMessage.Severity tipo) {
        FacesMessage message = new FacesMessage(tipo, msg, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }

    public Boolean getLayoutList() {
        return layoutList;
    }

    public void setLayoutList(Boolean layoutList) {
        this.layoutList = layoutList;
    }

    public Boolean getLayoutForm() {
        return layoutForm;
    }

    public void setLayoutForm(Boolean layoutForm) {
        this.layoutForm = layoutForm;
    }

    public Boolean getLayoutView() {
        return layoutView;
    }

    public void setLayoutView(Boolean layoutView) {
        this.layoutView = layoutView;
    }

}
