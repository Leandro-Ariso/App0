package multivac.app0;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;


public class MainActivity extends AppCompatActivity {

    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.button_main_1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuperActivityToast.create(MainActivity.this, new Style(), Style.TYPE_BUTTON)
                        //.setButtonText("UNDO")
                        //.setButtonIconResource(R.drawable.selector_button_standard)
                        //.setOnButtonClickListener("good_tag_name", null, null)
                        .setProgressBarColor(Color.WHITE)
                        .setText("this works!")
                        .setDuration(Style.DURATION_LONG)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(getResources().getColor(R.color.accent, getTheme()))
                        .setAnimations(Style.ANIMATIONS_POP).show();
            }
        });
    }
}
