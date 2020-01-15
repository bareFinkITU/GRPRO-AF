package SubModel;

import javafx.scene.image.Image;
    //subklasse af Media, da de indeholder samme felter, dog indeholder Movie en mere, som er year.
public class Movie extends Media {
    private int year;

    public Movie(String title, String[] genre, double rating, Image cover, int year) {
        super(title, genre, rating, cover); //kalder super konstruktoren
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}