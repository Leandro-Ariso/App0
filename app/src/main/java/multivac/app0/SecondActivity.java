package multivac.app0;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText phoneEditText;
    private ImageButton phoneCallButton;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        phoneEditText = findViewById(R.id.SecondActivity_PhoneText);
        phoneCallButton = findViewById(R.id.SecondActivity_PhoneButton);

        phoneCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneEditText.getText().toString();

                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    //Pedir el permiso de hacer llamadas.
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                } else {
                    Toast.makeText(SecondActivity.this, "Ingrese un número de teléfono válido.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE) && result == PackageManager.PERMISSION_GRANTED) {
                    //Se concedió el permiso.
                    String phoneNumber = phoneEditText.getText().toString();
                    Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(SecondActivity.this, "Permiso no concedido.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    startActivity(intentCall);
                } else {
                    Toast.makeText(SecondActivity.this, "Permiso no concedido.", Toast.LENGTH_SHORT).show();
                }

            default:

                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
