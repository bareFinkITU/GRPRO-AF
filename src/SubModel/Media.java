package SubModel;

import javafx.scene.image.Image;

public class Media{
    protected String title;
    protected String[] genre;
    //genre er i et array da vi fandt det nemmest, at arbejde med ... alle film/serier indeholder ikke alle lige mange genre
    protected double rating;
    protected Image cover; //af typen image, da det er et billede.


    public Media (String title, String[] genre, double rating, Image cover)  {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.cover = cover;
    }
        //metoder til at returnere dem, således de kan hentes i andre metoder.
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

}
