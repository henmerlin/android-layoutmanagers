package android.br.atividade.model;

import android.br.atividade.model.entity.PontoTuristico;

import java.util.List;

/**
 * Created by G0042204 on 23/04/2017.
 */

public class TurismoSingleton {
    private static final TurismoSingleton ourInstance = new TurismoSingleton();

    private Double total;

    private List<PontoTuristico> escolhas;

    private List<PontoTuristico> pontos;

    private TurismoSingleton() {
        this.total = 0d;
    }

    public static TurismoSingleton getInstance() {
        return ourInstance;
    }

    public Double getTotal() {
        return total;
    }

    public List<PontoTuristico> getEscolhas() {
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
        if(this.pontos != null){
            return;
        }
        this.pontos = pontos;
    }
}
