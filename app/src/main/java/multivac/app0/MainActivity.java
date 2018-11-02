package multivac.app0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button button_next;
    private EditText text_userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setLogo(R.mipmap.ic_myicon_round);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        button_next = findViewById(R.id.button_main_1);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = text_userName.getText().toString();

                if (user != null && !user.isEmpty()) {
                    Repository.getInstance().setUserName(user);

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Ingrese un nombre de usuario.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
