package TODO_CHANGE_MY_NAME;

import TODO_CHANGE_MY_NAME.Media;
import javafx.scene.image.Image;

public class Movie extends Media {
    private int year;

    public Movie(String title, String[] genre, double rating, Image cover, int year) {
        super(title, genre, rating, cover);
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
