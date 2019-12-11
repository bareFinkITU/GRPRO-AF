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
        String pwVal = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}"; //regex
        if( name == null ){
            throw new NullPointerException("Please input a name"); //overflødig?
        }else if(name.length() > 16){
            throw new IllegalArgumentException("Name can be at most 16 characters long");
        }else{
            this.name = name;
        }
        String uppercase = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        if(username.length() > 16){
            throw new IllegalArgumentException("Username can be at most 16 characters long");
        }else{
            this.username = username;
        }
        if(!password.matches(pwVal)){
            throw new IllegalArgumentException("Password must fulfill the following requirements: " +
                    "\n at least 1 Uppercase Character " +
                    "\n at least 1 Lowercase Character" +
                    "\n at least 1 digit" +
                    "\n must be 8 characters long");
        }else {
            this.password = password;
        }
        this.password = password;
        if(!email.contains("@") && !email.contains(".")) {
            throw new validRegistration("Please enter a valid email");
        }else{
            this.email = email;
        }

        if(age <= 14){ // kontrollerer at age er 15 eller større for, at kunne registrere en bruger
            throw new validRegistration("Age must be 15 or greater to register a user");
        }else if(age>110) {
            throw new validRegistration("Are you really older than 110 years old?");
        }
        profiles = new ArrayList<>();
        Profiles firstProfiles = new Profiles(uppercase,age);
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


    public void addProfile(Profiles p){

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
