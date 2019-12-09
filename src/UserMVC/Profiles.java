package UserMVC;

import controller.ContentController;
import model.Content;

import java.util.ArrayList;
import java.util.List;

public class Profiles {
    private String name;
    private List<Content> favorites; //enkelte profils favorit liste for film/serier
    private int age;

    public Profiles(String name, int age){
        favorites = new ArrayList<Content>();
        this.name = name;
        this.age = age;
    }

    public void isUnderAged(){
        //tjekker om brugeren er underages. Denne metode bruges i andre klasser
        boolean isUnderAged = age <= 14;
    }

    public List<Content> getFavorites(){ //returnerer favoritlisten
        return favorites;
    }

    public void addContent(Content c){ //tilføjer content til favoritlisten
        favorites.add(c);
    }

    public void removeContent(Content c){ //fjerner content fra favoritlisten
        favorites.remove(c);
    }

    public void editProfileName(String s){ //ændring af profilnavn
        name = s;
    }

    public int getAge(){ //indhentning af alder til en gældende profil. Måske overflødig eftersom vi har "isUnderAges()"?
        return age;
    }

    public void setAge(int age){ //ændring af alder til en gældende profil
        this.age = age;
    }




}
