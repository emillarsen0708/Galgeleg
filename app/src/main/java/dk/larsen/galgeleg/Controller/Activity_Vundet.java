package dk.larsen.galgeleg.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.larsen.galgeleg.R;

public class Activity_Vundet extends AppCompatActivity {

    private TextView duVandt, opsumVundet, ordetVarVundet;
    private Button gåTilStartVundet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);
        Bundle bundle = getIntent().getExtras();

        duVandt = findViewById(R.id.du_vandt);
        opsumVundet = findViewById(R.id.opsum_vundet);
        gåTilStartVundet = findViewById(R.id.gå_til_start_vundet);

        gåTilStartVundet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        duVandt.setText(bundle.getString("galgelogik.getOrdet"));
        duVandt.setText("Du har vundet \n" + bundle.getInt("getWonInARow")+ " gang(e)");
        opsumVundet.setText("Der blev brugt \n" + bundle.getInt("galgelogik.getBrugteBogstaverSize") + " forsøg");
    }
    private void openMainActivity() {
        Intent intent = new Intent(this, Activity_Start.class);
        startActivity(intent);
    }
}