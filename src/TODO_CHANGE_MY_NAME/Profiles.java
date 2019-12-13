package TODO_CHANGE_MY_NAME;
import TODO_CHANGE_MY_NAME.Media;
import java.util.ArrayList;
import java.util.List;

public class Profiles {
    private String name;
    private List<Media> favorites; //enkelte profils favorit liste for film/serier
    private int age;

    public Profiles(String name, int age){
        favorites = new ArrayList<Media>();
        if(name.length() > 32 || name.length() < 2){
            throw new IllegalArgumentException("Name requirements: \n" +
                                                "minimum 2 characters long \n" +
                                                "maximum 32 characters long");
        }else{
            this.name = name.trim();
        }

        if(age <= 0){
            throw new IllegalArgumentException("Age must be greater than 0");
        }else if(age > 110 ){
            throw new IllegalArgumentException("Age must be less than 110");
        }else{
            this.age = age;
        }
    }

    //TODO Implementer underage
    public boolean isUnderAged(){
        //tjekker om brugeren er underages. Denne metode bruges i andre klasser
        if (age <= 14){
            return true;
        } else {
            return false;
        }
    }

    public List<Media> getFavorites(){ //returnerer favoritlisten
        return favorites;
    }

    public void addMedia(Media m){ //tilføjer content til favoritlisten
        favorites.add(m);
    }

    public void removeMedia(Media m){ //fjerner content fra favoritlisten
        favorites.remove(m);
    }

    //TODO implementer eller slet?
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
    }




}
