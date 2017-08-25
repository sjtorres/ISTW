/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.controle;

import br.com.sistemaM.entidade.Material;
import br.com.sistemaM.facade.AbstractFacade;
import br.com.sistemaM.facade.MaterialFacade;
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
public class MaterialControle extends AbstractControle<Material> implements Serializable {

    @Inject
    private MaterialFacade materialFacade;
    private Material material;

    public MaterialControle() {
        super(Material.class);
    }

    @Override
    public AbstractFacade<Material> getFacade() {
        return materialFacade;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
