package android.br.atividade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button linear, grid, tableLinear, relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear = (Button) findViewById(R.id.linear);
        grid = (Button) findViewById(R.id.grid);
        tableLinear = (Button) findViewById(R.id.tableLinear);
        relative = (Button) findViewById(R.id.frameLinear);

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doIntent(LinearLayoutActivity.class);
            }
        });


    }

    protected void doIntent(Class c){
        Intent i = new Intent(this, c);
        startActivity(i);
    }
}
