package android.br.atividade;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


public class LinearLayoutActivity extends AppCompatActivity {

    String[]  nomesPontos;
    String[]  descsPontos;
    TypedArray imagensPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);


        //Recupera a View da lista a ser utilizada
        ListView listView = (ListView) findViewById(R.id.ListView01);

        //Recupera o contexto desta app
        Context ctx = getApplicationContext();
        //Recupera ref para resources
        Resources res = ctx.getResources();

        nomesPontos = res.getStringArray(R.array.nomes_pontos);
        descsPontos = res.getStringArray(R.array.descs_pontos);
        imagensPontos = res.obtainTypedArray(R.array.imagens_pontos);

    }

    public void sendWhatsApp() {

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
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
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }
}
