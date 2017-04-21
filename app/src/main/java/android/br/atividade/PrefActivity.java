package android.br.atividade;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/*
Esta classe extende a PreferenceActivity e é invocada através de um Intent a partir da MainActivity.
PrefActivity é responsável por carregar o Layout com as possibilidades de preferências a serem
disponibilizadas ao usuário. Uma instância de SharedPreferences é compartilhada entre esta atividade
e a MainActivity.
*/
public class PrefActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //carrega o Layout de preferências, o PreferenceScreen
        addPreferencesFromResource(R.xml.preferences);

        //cria um Intent de resultado e seta resultado OK
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
    }
}
