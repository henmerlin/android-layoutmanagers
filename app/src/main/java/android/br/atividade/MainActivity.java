package android.br.atividade;

import android.annotation.SuppressLint;
import android.br.atividade.dal.PontoTuristicoDAO;
import android.br.atividade.model.TurismoSingleton;
import android.br.atividade.model.entity.PontoTuristico;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
            SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    Log.d("MAIN", "onSharedPreferenceChanged: " + key);
                    setUsersPreferences();
                }
            };


    String modo_envio, nome;
    Integer nrAcompanhantes;
    Boolean incluiAlmoco;

    /**
     * Componentes
     */
    Button comprar;
    ListView listView;

    TurismoSingleton s = TurismoSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Recupera o contexto desta app
        Context ctx = getApplicationContext();
        //Recupera ref para resources
        PontoTuristicoDAO dao = new PontoTuristicoDAO(ctx);
        s.setPontos(dao.listar());

        //Recupera a View da lista a ser utilizada
        listView = (ListView) findViewById(R.id.ListView01);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
                s.getPontos().get(position).setSelected(!checkbox.isChecked());
                checkbox.setChecked(!checkbox.isChecked());
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        this.carregarCampos();


        // Listener do Botão Compra
        comprar = (Button) findViewById(R.id.comprar);
        comprar.setOnClickListener(this);


    }

    /**
     * Listener do Botão Compra
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.carregarCampos();
    }

    protected void carregarCampos(){
        listView.setAdapter(null);
        listView.setAdapter(new BaseAdapter() {
            //retorna o número de ítens total da lista
            public int getCount() {
                return s.getPontos().size();
            }

            //retorna um item em específico, o nome de um país em dada posição da lista
            public PontoTuristico getItem(int position) {
                return s.getPontos().get(position);
            }

            //retorna o Id de um ítem em dada posição
            //por opção de implementação o id é o mesmo que a posição
            //mas o Android e RecyclerView não sabem disso e nem devem presumí-lo
            public long getItemId(int position) {
                return position;
            }

            //retorna as Views que devem ser preenchidas quando a Janela exibir
            //determinada posição ou linha da Lista
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.list_row, null);

                PontoTuristico p = s.getPontos().get(position);

                ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                imageView.setImageDrawable(p.getImagem());

                TextView header = (TextView) view.findViewById(R.id.textViewHeader);
                header.setText(p.getNome());

                TextView desc = (TextView) view.findViewById(R.id.textViewDesc);
                desc.setText(p.getDescricao());

                CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
                checkbox.setChecked(p.getSelected());

                return view;
            }
        });
    }


    public void setUsersPreferences() {
        modo_envio = sharedPreferences.getString("modo_envio", "SMS");
        nome = sharedPreferences.getString("nome", "Nome Completo");
        nrAcompanhantes = sharedPreferences.getInt("nrAcompanhantes", 1);
        incluiAlmoco = sharedPreferences.getBoolean("incluiAlmoco", false);
    }

    public void sendWhatsApp() {

        PackageManager pm = getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    private void sendMail() {
        String addresses[] = {"luis.trevisan@up.edu.br"};
        Intent intent = new Intent(Intent.ACTION_SENDTO); //apenas e-mail
        intent.setData(Uri.parse("mailto:"));//apenas e-mail
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Resumo Formulário de " + "YOUR TEXT HERE");
        intent.putExtra(Intent.EXTRA_TEXT, "YOUR TEXT HERE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void sendSMS() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:987901382")); // This
        intent.putExtra("sms_body", "YOUR TEXT HERE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
