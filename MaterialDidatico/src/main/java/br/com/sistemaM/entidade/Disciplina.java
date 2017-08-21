/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.envers.Audited;

/**
 *
 * @author tiago
 */
@Entity
@Audited
@Table(name = "disciplina")
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dis_id", nullable = false)
    private Long id;
    @Column(name = "dis_nome", nullable = false)
    private String nome;
    @Column(name = "dis_cod_acesso", nullable = false)
    private String codAcesso;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "disciplina",
            orphanRemoval = true)
    private List<ItemDisciplina> itensDisciplina = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodAcesso() {
        return codAcesso;
    }

    public void setCodAcesso(String codAcesso) {
        this.codAcesso = codAcesso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<ItemDisciplina> getItensDisciplina() {
        return itensDisciplina;
    }

    public void setItensDisciplina(List<ItemDisciplina> itensDisciplina) {
        this.itensDisciplina = itensDisciplina;
    }

    @Transient
    private ItemDisciplina itemDisciplina = new ItemDisciplina();

    public void addItem(ItemDisciplina item) throws Exception {
        if (!itensDisciplina.contains(item)) {
            itensDisciplina.add(item);
        } else {
            throw new Exception("O ItemDisciplina já está adicionado");
        }
    }

    public void removeItem(ItemDisciplina item) {
        itensDisciplina.remove(item);
    }

    public ItemDisciplina getItemDisciplina() {
        return itemDisciplina;
    }

    public void setItemDisciplina(ItemDisciplina itemDisciplina) {
        this.itemDisciplina = itemDisciplina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
