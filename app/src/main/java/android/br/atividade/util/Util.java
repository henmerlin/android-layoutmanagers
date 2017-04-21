package android.br.atividade.util;

import java.util.List;

/**
 * Created by G0042204 on 21/04/2017.
 */

public class Util
{
    public static String listToPortuguese(List<String> list){
        if(list.isEmpty()){
            return ".";
        }
        StringBuilder b = new StringBuilder();
        int i = 0;
        for (String a: list) {
            if(i == list.size() - 2){
                b.append(a + " e ");
            }else if(i == list.size() - 1) {
                b.append(a + ".");
            }else{
                b.append(a + ", ");
            }
            i++;
        }
        return b.toString();
    }
}
