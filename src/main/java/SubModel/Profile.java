package SubModel;
import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String name;
    private List<Media> favorites; //enkelte profils favorit liste for film/serier
    private int age;

    public Profile(String name, int age){
        favorites = new ArrayList<Media>();
        if((name.length() > 32 || name.length() < 2)){
            //krav om længden og indhold for et navn.
            throw new IllegalArgumentException("Name requirements: \n" +
                                                "minimum 2 characters long \n" +
                                                "maximum 32 characters long");
        }else{
            this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase(); //Gør det første bogstav stort når en profil oprettes
        }

        if(age <= 0){ //man skal mindst være 1 år for, at få en profil.
            throw new IllegalArgumentException("Age must be greater than 0");
        }else if(age > 110 ){
            throw new IllegalArgumentException("Age must be less than 110");
        }else{
            this.age = age;
        }
    }

    public boolean isUnderAged(){
        //tjekker om brugeren er underages. Denne metode bruges i andre klasser
        if (age <= 14){
            return true;
        } else {
            return false;
        }
    }
    //return metode
    public List<Media> getFavorites(){ //returnerer favoritlisten
        return favorites;
    }
    //tilføj til favoritliste
    public void addMedia(Media m){ //tilføjer content til favoritlisten
        favorites.add(m);
    }
    //fjern fra favoritliste
    public void removeMedia(Media m){ //fjerner content fra favoritlisten
        favorites.remove(m);
    }

    public String getName(){
        return name;
    }

}
