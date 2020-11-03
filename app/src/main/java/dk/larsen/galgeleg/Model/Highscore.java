package dk.larsen.galgeleg.Model;

// Det blev aldrig til mere, med highscoren. Ville lave listview

public class Highscore {


    private String name;
    int wonInARow, lossInARow;

    public Highscore(String name, int wonInARow, int lossInARow) {
        this.name = name;
        this.wonInARow = wonInARow;
        this.lossInARow = lossInARow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinCounter() {
        return wonInARow;
    }

    public void setWinCounter(int winCounter) {
        this.wonInARow = winCounter;
    }

    public int getLossCounter() {
        return lossInARow;
    }

    public void setLossCounter(int lossCounter) {
        this.lossInARow = lossCounter;
    }
}
