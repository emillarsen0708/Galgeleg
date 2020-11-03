package dk.larsen.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Galgeleg extends AppCompatActivity implements View.OnClickListener {

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

        winLoseCountUpdate();
    }

    @Override
    public void onClick (View v) {
        if (v == enter) {
            gætEtBogstav();
        } else if (v == faaSvar) {
            Toast.makeText(this, galgelogik.getOrdet(), Toast.LENGTH_SHORT).show();
        } else if (v == nytSpil) {
            galgelogik.startNytSpil();
            galgeSkift();
            galgeSpilOrdet.setText(galgelogik.getSynligtOrd());
            brugteBogstav.setText("");
            spilSlut = false;
            Toast.makeText(this, "Et nyt spil er startet", Toast.LENGTH_SHORT).show();
        } else if (v == hentOrdDr) {
            getDrOrd();
        }
        gætOrd.setText("");

        }

    @Override
    protected void onResume() {
        super.onResume();
        galgelogik.startNytSpil();
        galgeSkift();
        galgeSpilOrdet.setText(galgelogik.getSynligtOrd());
        brugteBogstav.setText("");
        spilSlut = false;
        winLoseCountUpdate();
    }

    public void galgeSkift() {
        if (galgelogik.getAntalForkerteBogstaver() == 0) {
            galgen.setImageResource(R.drawable.galge);
        } else if (galgelogik.getAntalForkerteBogstaver() == 1) {
            galgen.setImageResource(R.drawable.forkert1);
        } else if (galgelogik.getAntalForkerteBogstaver() == 2) {
            galgen.setImageResource(R.drawable.forkert2);
        } else if (galgelogik.getAntalForkerteBogstaver() == 3) {
            galgen.setImageResource(R.drawable.forkert3);
        } else if (galgelogik.getAntalForkerteBogstaver() == 4) {
            galgen.setImageResource(R.drawable.forkert4);
        } else if (galgelogik.getAntalForkerteBogstaver() == 5) {
            galgen.setImageResource(R.drawable.forkert5);
        } else if (galgelogik.getAntalForkerteBogstaver() == 6) {
            galgen.setImageResource(R.drawable.forkert6);
        }
    }

    public void openActivityVundet() {
        Intent winIntent = new Intent(this, Activity_Vundet.class);
        winIntent.putExtra("galgelogik.getAntalForkerteBogstaver", galgelogik.getAntalForkerteBogstaver());
        winIntent.putExtra("galgelogik.getBrugteBogstaverSize", galgelogik.getBrugteBogstaver().size());
        winIntent.putExtra("galgelogik.getOrdet", galgelogik.getOrdet());
        winIntent.putExtra("getWonInARow", wonInARow);
        startActivity(winIntent);
    }

    public void openActivityTabt() {
        Intent lossIntent = new Intent(this, Activity_Tabt.class);
        lossIntent.putExtra("galgelogik.getAntalForkerteBogstaver", galgelogik.getAntalForkerteBogstaver());
        lossIntent.putExtra("galgelogik.getBrugteBogstaverSize", galgelogik.getBrugteBogstaver().size());
        lossIntent.putExtra("galgelogik.getOrdet", galgelogik.getOrdet());
        lossIntent.putExtra("getLossInARow", lossInARow);
        startActivity(lossIntent);
    }

    public void openActivitySlut() {
        Intent finishedIntent = new Intent(this, Activity_Slut.class);
        finishedIntent.putExtra("galgelogik.getAntalForkerteBogstaver", galgelogik.getAntalForkerteBogstaver());
        finishedIntent.putExtra("galgelogik.getBrugteBogstaverSize", galgelogik.getBrugteBogstaver().size());
        finishedIntent.putExtra("galgelogik.getOrdet", galgelogik.getOrdet());
        finishedIntent.putExtra("getLossCounter", lossInARow);
        startActivity(finishedIntent);
    }

    public void gætEtBogstav() {
        galgelogik.gætBogstav(gætOrd.getText().toString());
        galgeSpilOrdet.setText(galgelogik.getSynligtOrd());
        String currentWord = "";
        galgeSkift();
        for (int i = 0; i < galgelogik.getBrugteBogstaver().size(); i++) {
            currentWord = currentWord + "  " + galgelogik.getBrugteBogstaver().get(i);
        }

        if (enter.getText().toString().equals("")) {
            Toast.makeText(this, "Indtast bogstav, og tryk på gæt", Toast.LENGTH_SHORT).show();
        } else if (!enter.getText().equals("")) {
            if (!galgelogik.erSidsteBogstavKorrekt() && galgelogik.getAntalForkerteBogstaver() < 6) {
                if (galgelogik.getAntalForkerteBogstaver() == 1) {
                    Toast.makeText(this, "Du har gættet forkert " + (galgelogik.getAntalForkerteBogstaver()) + " gang.", Toast.LENGTH_SHORT).show();
                } else if (galgelogik.getAntalForkerteBogstaver() > 1) {
                    Toast.makeText(this, "Du har gættet forkert " + (galgelogik.getAntalForkerteBogstaver()) + " gange.", Toast.LENGTH_SHORT).show();
                }
            } else if (galgelogik.erSidsteBogstavKorrekt() && !galgelogik.erSpilletVundet()) {
                Toast.makeText(this, "Du gættede rigtigt: '" +gætOrd.getText().toString().toUpperCase() + "' indgår i ordet", Toast.LENGTH_SHORT).show();
            } else if (galgelogik.erSpilletVundet()) {
                Toast.makeText(this, "Du vandt, fedt mand", Toast.LENGTH_SHORT).show();
                if (!spilSlut) {
                    wonInARow++;
                }
                winCount.setText("W = " + wonInARow);
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("sWinCounter", wonInARow);
                editor.commit();
                spilSlut = true;
                openActivityVundet();
            } else if (galgelogik.erSpilletTabt()) {
                spilTabt();
            }
        }
        brugteBogstav.setText(currentWord);
    }

    public void spilTabt() {
        if (lossInARow >= 3) {
            openActivitySlut();
            spilSlut = true;
        }
        else if (lossInARow < 3) {
            Toast.makeText(this, "YOU LOSE", Toast.LENGTH_SHORT).show();
            if (!spilSlut) {
                lossInARow++;
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("sLossCounter", lossInARow);
                editor.commit();
                Log.d(sharedPreferences.getAll().toString(), "123123");
            }
            lossCount.setText("L = " + lossInARow);
            spilSlut = true;
            openActivityTabt();
        }
        winLoseCountUpdate();
    }

    public void getDrOrd() {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    galgelogik.hentOrdFraDr();
                    return "Success";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Fail: " + e;
                }
            }

            @Override
            protected void onPostExecute(Object result) {
                hentOrdDr.setText(result.toString());
                galgeSpilOrdet.setText(galgelogik.getSynligtOrd());
                brugteBogstav.setText("");
                spilSlut = false;
                galgeSkift();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hentOrdDr.setText("nyt DR ord");
                    }
                }, 5000);
            }
        }.execute();
    }



    public void winLoseCountUpdate(){
        winCount.setText("W = " + wonInARow);
        lossCount.setText("L = " + lossInARow);
    }
}