package android.br.atividade.dal;

import android.br.atividade.R;
import android.br.atividade.model.entity.PontoTuristico;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G0042204 on 23/04/2017.
 */

public class PontoTuristicoDAO {

    List<PontoTuristico> lst;

    public PontoTuristicoDAO(Context ctx) {
        Resources res = ctx.getResources();
        String[] nomesPontos = res.getStringArray(R.array.nomes_pontos);
        String[] descsPontos = res.getStringArray(R.array.descs_pontos);
        TypedArray imagensPontos = res.obtainTypedArray(R.array.imagens_pontos);
        lst = new ArrayList<>();
        for (int i = 0; i < nomesPontos.length; i++){
            lst.add(new PontoTuristico(i, nomesPontos[i], descsPontos[i], imagensPontos.getDrawable(i)));
        }

    }

    public List<PontoTuristico> listar(){
        return lst;
    }
}
