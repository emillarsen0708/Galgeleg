package dk.larsen.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Tabt extends AppCompatActivity {

    private TextView duTabte, opsumTabt, ordetVar;
    private Button gåTilStartTabt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt);
        Bundle bundle = getIntent().getExtras();

        duTabte = findViewById(R.id.du_tabte);
        opsumTabt = findViewById(R.id.opsum_tabt);
        ordetVar = findViewById(R.id.ordet_var);
        gåTilStartTabt = findViewById(R.id.gå_til_start_tabt);

        gåTilStartTabt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        duTabte.setText(bundle.getString("galgelogik.getOrdet"));
        duTabte.setText("Du har nu tabt \n" + bundle.getInt("getLossInARow")+ " gang(e)");
        opsumTabt.setText("Der blev brugt \n" + bundle.getInt("galgelogik.getBrugteBogstaverSize") + " forsøg");
        ordetVar.setText("Galgeordet var '" + bundle.getString("galgelogik.getOrdet")+ "'");
    }
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}