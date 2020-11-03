package dk.larsen.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button spil_knap;
    Button highscore_knap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spil_knap = findViewById(R.id.spil);
        highscore_knap = findViewById(R.id.highscore);
        spil_knap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openGalgeleg();
            }
        });
        highscore_knap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openHighscore();
            }
        });

            }
    private void openGalgeleg() {
        Intent spilIntent = new Intent (this, Activity_Galgeleg.class);
        startActivity(spilIntent);
    }

    private void openHighscore() {
        Intent highscoreIntent = new Intent (this, Activity_Highscore.class);
        startActivity(highscoreIntent);
    }

}