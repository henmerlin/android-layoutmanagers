package android.br.atividade.model.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by G0042204 on 22/04/2017.
 */

public class PontoTuristico {

    private Integer id;
    private String nome, descricao;
    private Drawable imagem;
    private Boolean selected;

    public PontoTuristico(Integer id, String nome, String descricao, Drawable imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.selected = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Drawable getImagem() {
        return imagem;
    }

    public void setImagem(Drawable imagem) {
        this.imagem = imagem;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
