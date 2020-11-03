package dk.larsen.galgeleg;

import dk.larsen.galgeleg.Model.Galgelogik;

//Ville gerne prøve at lave en Presenter, til MVP.

public class ActivityPresenter {

    private View view;
    private Galgelogik dal;


     public ActivityPresenter (View view) {
         this.dal = dal;
         this.view = view;
     }

     public interface View {
     }
}
