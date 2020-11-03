package dk.larsen.galgeleg.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import dk.larsen.galgeleg.R;

public class MainActivity extends AppCompatActivity {

    Button spil_knap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spil_knap = findViewById(R.id.spil);
        spil_knap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openGalgeleg();
            }
        });
            }
    private void openGalgeleg() {
        Intent spilIntent = new Intent (this, Activity_Galgeleg.class);
        startActivity(spilIntent);
    }


}