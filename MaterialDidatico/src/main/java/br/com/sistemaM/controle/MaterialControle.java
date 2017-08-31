/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.controle;

import br.com.sistemaM.entidade.Material;
import br.com.sistemaM.enums.NivelAcesso;
import br.com.sistemaM.facade.AbstractFacade;
import br.com.sistemaM.facade.DisciplinaFacade;
import br.com.sistemaM.facade.MaterialFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tiago
 */
@Named
@ViewScoped
public class MaterialControle extends AbstractControle<Material> implements Serializable {

    @Inject
    private MaterialFacade materialFacade;
    private Material material;
    @Inject
    private LoginControle loginControle;

    public MaterialControle() {
        super(Material.class);
    }

    @Override
    public AbstractFacade<Material> getFacade() {
        return materialFacade;
    }

    @Override
    public List<Material> getListar() throws Exception {
        if(loginControle.getUsuario().getNivelAcesso().equals(NivelAcesso.PROFESSOR)){
            return materialFacade.listarProfessor(loginControle.getUsuario().getLogin());
        } else if(loginControle.getUsuario().getNivelAcesso().equals(NivelAcesso.ALUNO)){
            return materialFacade.listarAluno(loginControle.getUsuario().getLogin());
        }
        return super.getListar();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
