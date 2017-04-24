package android.br.atividade.model;

import android.br.atividade.model.entity.PontoTuristico;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G0042204 on 23/04/2017.
 */
public class TurismoSingleton {

    private static final TurismoSingleton ourInstance = new TurismoSingleton();

    private Double total;

    private List<PontoTuristico> escolhas;

    private List<PontoTuristico> pontos;

    private Boolean almoco;

    private Integer nrPessoas;

    private Double txAlmoco;

    private String modoEnvio, nome;


    private TurismoSingleton() {
        this.almoco = false;
        this.escolhas = new ArrayList<>();
        this.nrPessoas = 0;
        this.txAlmoco = 15d;
        this.total = 0d;
    }

    public static TurismoSingleton getInstance() {
        return ourInstance;
    }

    public Double getTotal() {
        if (almoco) {
            txAlmoco = 30d;
        } else {
            txAlmoco = 15d;
        }
        total = (txAlmoco) * new Integer(1 + nrPessoas) * (this.getEscolhas().size());
        return total;
    }

    public List<PontoTuristico> getEscolhas() {
        escolhas = new ArrayList<>();
        for (PontoTuristico pts : pontos) {
            if (pts.getSelected()) {
                escolhas.add(pts);
            }
        }
        return escolhas;
    }

    public void setEscolhas(List<PontoTuristico> escolhas) {
        this.escolhas = escolhas;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<PontoTuristico> getPontos() {
        return pontos;
    }

    public void setPontos(List<PontoTuristico> pontos) {
        if (this.pontos != null) {
            return;
        }
        this.pontos = pontos;
    }

    public void setNrPessoas(Integer nrPessoas) {
        this.nrPessoas = nrPessoas;
    }

    public void setAlmoco(Boolean almoco) {
        this.almoco = almoco;
    }

    public void setModoEnvio(String modoEnvio) {
        this.modoEnvio = modoEnvio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
