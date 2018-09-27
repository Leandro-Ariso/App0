package multivac.app0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button boton;
    private final String greeter = "Hello There!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.button_main_1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("greeter", greeter);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();

        if(bundle != null && bundle.containsKey("response")) {
            String res = (String) bundle.get("response");
            Toast.makeText(MainActivity.this, res, Toast.LENGTH_LONG).show();
        }
    }
}
