package model;

import javafx.scene.image.Image;
import java.awt.*;
import javafx.scene.image.Image;


public class Show extends Content{
    protected String runtime;
    protected int startYear;
    protected int endYear;
    protected String seasons;

    public Show(String title, String[] genre, double rating, Image cover, String runtime, int startYear, int endYear, String seasons) {
        super(title, genre, rating, cover);
        this.runtime = runtime;
        this.startYear = startYear;
        this.endYear = endYear;
        this.seasons = seasons;
    }

    public String getSeasons() {
        return seasons;
    }
    public int getEndYear() {
        return endYear;
    }
    public int getStartYear() {
        return startYear;
    }
    public String getRuntime() { return runtime;}
}
