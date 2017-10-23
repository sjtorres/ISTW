/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaM.controle;

import br.com.sistemaM.entidade.Disciplina;
import br.com.sistemaM.entidade.Material;
import br.com.sistemaM.facade.AbstractFacade;
import br.com.sistemaM.facade.MaterialFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import java.util.Date;
import org.primefaces.component.filedownload.FileDownloadActionListener;
import org.primefaces.component.filedownload.FileDownloadTagHandler;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author tiago
 */
@Named
@ViewScoped
public class MaterialControle extends AbstractControle<Material> implements Serializable {

    @Inject
    private MaterialFacade habilidadeFacade;
    private Material habilidade;
    private String extensao;
    private String nomeArq;
    private Date dataCadastro;
    private Disciplina disciplina;
    private String nome;

    public MaterialControle() {
        super(Material.class);
    }

    @Override
    public AbstractFacade<Material> getFacade() {
        return habilidadeFacade;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public String getNomeArq() {
        return nomeArq;
    }

    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Material getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Material habilidade) {
        this.habilidade = habilidade;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        try {
            UploadedFile arq = event.getFile();
            InputStream in = new BufferedInputStream(arq.getInputstream());
            String caminho = "C:\\Users\\Belatriz\\Documents\\GitHub\\Projeto\\MaterialDidatico\\src\\main\\webapp\\imagens\\Upload\\" + arq.getFileName();
            File fileAnexo = new File(caminho);
            fileAnexo.getParentFile().mkdirs();
            extensao = arq.getFileName().substring(arq.getFileName().lastIndexOf("."), arq.getFileName().length());
            nomeArq = arq.getFileName();
            FileOutputStream fout = new FileOutputStream(caminho);
            while (in.available() != 0) {
                fout.write(in.read());
            }
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleFileDownload() {
         StreamedContent file;
        String caminho = "C:\\Users\\Belatriz\\Documents\\GitHub\\Projeto\\MaterialDidatico\\src\\main\\webapp\\imagens\\Upload\\" +  super.getEntidade().getNomearq();
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(caminho);
        file = new DefaultStreamedContent(stream, "application/" + super.getEntidade().getFormato(), super.getEntidade().getNomearq());
    }

    @Override
    public String salvar() {
        try {
             
            Material m = new Material();
            m.setNome(nome);
            m.setNomearq(nomeArq);
            m.setFormato(extensao);
            m.setDataCadastro(new Date());
            m.setDisciplina(disciplina);
            habilidadeFacade.salvar(m);
            mensagem("Salvo com sucesso: ", FacesMessage.SEVERITY_INFO);
            voltar();

        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;

    }

    private void mensagem(String salvo_com_sucesso_, FacesMessage.Severity SEVERITY_INFO) {
      
    }
}
