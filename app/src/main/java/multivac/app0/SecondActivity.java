package multivac.app0;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private SeekBar ageSeekBar;
    private TextView ageTextView;
    private RadioGroup greetingTypeGroup;
    private RadioButton helloRadioButton;
    private RadioButton byeRadioButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setLogo(R.mipmap.ic_myicon_round);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        ageSeekBar = findViewById(R.id.secondActivity_AgeSeekBar);
        ageTextView = findViewById(R.id.secondActivity_ageTextView);
        greetingTypeGroup = findViewById(R.id.secondActivity_typeRadioGroup);
        helloRadioButton = findViewById(R.id.secondActivity_helloRadioButton);
        byeRadioButton = findViewById(R.id.secondActivity_byeRadioButton);
        nextButton = findViewById(R.id.secondActivity_nextButton);

        ageTextView.setText("Seleccionar Edad");

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                ageTextView.setText(progress + " años");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Repository.getInstance().setAge(progress);
            }
        });

        greetingTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedButton = findViewById(checkedId);
            }

        });


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

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intentCamera, CAMERA_PICTURE_CODE);
            }
        });

        webPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = webPageEditText.getText().toString();

                if (url != null && !url.isEmpty()) {
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url));
                    startActivity(intentWeb);
                } else {
                    Toast.makeText(SecondActivity.this, "Ingrese una URL válida.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case CAMERA_PICTURE_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageBitmap, 120, 120, false);
                    cameraButton.setImageBitmap(scaledBitmap);

                } else {
                    Toast.makeText(SecondActivity.this, "Por favor tome una foto.", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
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
