package UserMVC;

import model.Content;
import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String username;
    private String password;
    private int age;
    private String email;
    private List<Profiles> profiles;

    private boolean underAge;
    private Profiles selectedProfile;


    public User(String name, String username, String password, String email, int age){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;

        if(age <= 14){ // kontrollerer at age er 15 eller større for, at kunne registrere en bruger
            throw new validRegistration("Age must be 15 or greater to register a user");
        }else {
            this.age = age;
        }
        profiles = new ArrayList<>();
        Profiles firstProfiles = new Profiles(name,age);
        profiles.add(firstProfiles);
        selectedProfile = firstProfiles;


    }

    /*public void contentAccess(String family){
        String family = "family";
        if(!underAge){
            //gør ikke noget da de er 15 år eller mere
        }else{

        }
    }*/


    public void addProfile(Profiles p){ //tilføjer en profil til listen af profiles
        profiles.add(p);
    }

    public void removeProfile(Profiles p ){ //fjerner en profil fra listen af profiles
        profiles.remove(p);
    }

    public void setPassword(String password) {
        this.password = password;
    }
                                    //følgende metoder er get -og setmetoder
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public List<Profiles> getProfiles(){
        return profiles;
    }

    public Profiles getSelectedProfile(){
        return selectedProfile;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public void setFavorites(List<Movie> favorites) {
        this.favorites = favorites;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setSelectedProfile(Profiles profile){
        selectedProfile = profile;
    }

    public void show(){
        //sout test metode
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase());
        System.out.println("E-mail: " + email);
        System.out.println("Age: " + age);
    }

}
