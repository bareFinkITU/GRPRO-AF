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

    public User(String name, String username, String password, String email, int age){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        profiles = new ArrayList<>();
        profiles.add(new Profiles(name, age));

        if(age >= 15){
            boolean underAge = false;
        }
    }

    public void addProfile(Profiles p){
        profiles.add(p);
    }

    public void removeProfile(Profiles p ){
        profiles.remove(p);
    }

   // public void changeProfileName()



    public void setPassword(String password) {
        this.password = password;
    }

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

    public void show(){
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase());
        System.out.println("E-mail: " + email);
        System.out.println("Age: " + age);
    }

}
