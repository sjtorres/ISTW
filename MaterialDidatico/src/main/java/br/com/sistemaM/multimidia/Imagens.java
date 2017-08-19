/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.multimidia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author tiago
 */
@ManagedBean
public class Imagens implements Serializable {

    private List<String> images;
    private Integer c;
    
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (c = 1; c <= 2; c++) {
            images.add("image" + c + ".jpg");
        }
    }
    
    public List<String> getImages() {
        return images;
    }

}
