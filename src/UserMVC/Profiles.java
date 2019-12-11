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
        if( name == null ){
            throw new NullPointerException("Please input a name");
        }else if(name.length() > 16 || name.length() < 4){
            throw new IllegalArgumentException("Name requirements: \n" +
                    "minimum 4 characters long \n" +
                    "maximum 16 characters long");
        }else{
            this.name = name;
        }
        if(age <= 0){
            throw new IllegalArgumentException("Age must be greater than 0");
        }else if(age > 110 ){
            throw new IllegalArgumentException("Age must be less than 110");
        }else{
            this.age = age;
        }
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

    public String getName(){
        return name;
    }


    public int getAge(){ //indhenting af alder til en gældende profil. Måske overflødig eftersom vi har "isUnderAged()"?
        return age;
    }

    public void setAge(int age){
        this.age = age;
    } //ændring af alder til en gældende profil




}
