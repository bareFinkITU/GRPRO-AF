package model;

import javafx.scene.image.Image;

import java.awt.*;
import javafx.scene.image.Image;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Comparator;

public class Content{
    protected String title;
    protected String[] genre;
    protected double rating;
    protected Image cover;


    public Content (String title, String[] genre, double rating, Image cover)  {
        super();
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.cover = cover;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(Image cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String[] getGenre() {
        return genre;
    }

    public Image getCover() {
        return cover;
    }

    public String display() {
        String preFix = title; /*+ " " + rating + " genre(s): " + String.join(", ", genre); */
        if(this instanceof Movie){
            return preFix + " " + ((Movie) this).getYear();
        }else{
            return preFix + " " + ((Show) this).getStartYear() + " end " + ((Show) this).getEndYear();
        }
    }

    //TODO sort by year + sort by title

/*    static final Comparator<Content> BY_GENRE =
            new Comparator<Genre>() {
                //
            };*/

}
