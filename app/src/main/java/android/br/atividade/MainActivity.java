package android.br.atividade;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] nomesPontos;
    String[] descsPontos;
    TypedArray imagensPontos;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        //Recupera a View da lista a ser utilizada
        ListView listView = (ListView) findViewById(R.id.ListView01);

        //Recupera o contexto desta app
        Context ctx = getApplicationContext();
        //Recupera ref para resources
        Resources res = ctx.getResources();

        nomesPontos = res.getStringArray(R.array.nomes_pontos);
        descsPontos = res.getStringArray(R.array.descs_pontos);
        imagensPontos = res.obtainTypedArray(R.array.imagens_pontos);

        listView.setAdapter(new BaseAdapter() {
            //retorna o número de ítens total da lista
            public int getCount() {
                return nomesPontos.length;
            }

            //retorna um item em específico, o nome de um país em dada posição da lista
            public Object getItem(int position) {
                return nomesPontos[position];
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

                ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                imageView.setImageDrawable(imagensPontos.getDrawable(position));

                TextView header = (TextView) view.findViewById(R.id.textViewHeader);
                header.setText(nomesPontos[position]);

                TextView desc = (TextView) view.findViewById(R.id.textViewDesc);
                desc.setText(descsPontos[position]);


                //recumeramos a iv e setamos a imagem correta
                //ImageView iv = (ImageView) view.findViewById(R.id.img_01_view);
                // iv.setImageDrawable(bandeiras.getDrawable(position));
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
