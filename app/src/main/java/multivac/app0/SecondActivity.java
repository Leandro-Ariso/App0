package multivac.app0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView text;
    private Button boton;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = findViewById(R.id.textView_SecondActivity_1);
        boton = findViewById(R.id.button_SecondActivity_1);

        Bundle bundle = getIntent().getExtras();

        String caja;

        if(bundle != null) {
            caja = (String) bundle.get("greeter");
            response = "General Kenobi!";
        }
        else {
            caja = "Segment Fault";
            response = "KHAAAAAAAAAN!";
        }

        text.setText(caja);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("response", response);
                startActivity(intent);
            }
        });
    }
}
