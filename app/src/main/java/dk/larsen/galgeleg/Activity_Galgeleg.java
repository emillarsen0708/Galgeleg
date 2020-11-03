package dk.larsen.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_Galgeleg extends AppCompatActivity {

    private Galgelogik galgelogik = new Galgelogik();
    private EditText gætOrd;
    private Button enter, faaSvar, hentOrdDr, nytSpil;
    private ImageView galgen;
    private TextView galgeSpilOrdet, brugteBogstav, lossCount, winCount;
    private int wonInARow = 0;
    private int lossInARow = 0;
    private boolean spilSlut = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgeleg);

        gætOrd = findViewById(R.id.gæt_ord);
        enter = findViewById(R.id.enter);
        faaSvar = findViewById(R.id.få_svar);
        hentOrdDr = findViewById(R.id.hent_ord_dr);
        nytSpil = findViewById(R.id.nyt_spillet);
        galgen = findViewById(R.id.galgen);
        galgeSpilOrdet = findViewById(R.id.galge_ordet);
        brugteBogstav = findViewById(R.id.brugte_bogstaver);
        lossCount = findViewById(R.id.textViewLossCounter);
        winCount = findViewById(R.id.textViewWinCounter);

        enter.setOnClickListener((View.OnClickListener) this);
        faaSvar.setOnClickListener((View.OnClickListener) this);
        hentOrdDr.setOnClickListener((View.OnClickListener) this);
        nytSpil.setOnClickListener((View.OnClickListener) this);

        gætOrd.setShowSoftInputOnFocus(false);

        galgeSpilOrdet.setText(galgelogik.getSynligtOrd());

        wonInARow = getPreferences(wonInARow).getInt("tWonInARow", wonInARow);
        lossInARow = getPreferences(lossInARow).getInt("tLossInARow", lossInARow);







    }
}