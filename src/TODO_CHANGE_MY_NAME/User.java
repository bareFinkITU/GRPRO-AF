package TODO_CHANGE_MY_NAME;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String username;
    private String password;
    private int age;
    private String email;
    private String uppercase;
    private List<Profiles> profiles;
    //TODO implementering af underage
    private boolean underAge;
    private Profiles selectedProfile;

    public User(String name, String username, String password, String email, int age){

        if((name.length() > 32 || name.length() < 2) && name.matches("(?=.*[a-z])")){
            throw new IllegalArgumentException("Name requirements: \n" +
                                               "minimum 2 characters long \n" +
                                               "maximum 32 characters long");
        }else{
            this.name = name.trim().replaceAll(" +", " ");
        }
        if (name.length() < 16){
            uppercase = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        } else {
            uppercase = name.substring(0,1).toUpperCase() + name.substring(1,16).toLowerCase();
        }
        if((username.length() > 16 || username.length() < 4)) {
            throw new IllegalArgumentException("Username requirements: \n" +
                                               "minimum 4 characters long \n" +
                                               "maximum 16 characters long");
        }else{
            this.username = username.trim();
        }
        if(!password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")){
            throw new IllegalArgumentException("Password must fulfill the following requirements: " +
                                               "\n at least 1 Uppercase Character " +
                                               "\n at least 1 Lowercase Character" +
                                               "\n at least 1 number" +
                                               "\n must be at least 8 characters long");
        }else {
            this.password = password;
        }
        this.password = password;
        if(!email.matches("^(.+)@(.+)$")) {
            throw new IllegalArgumentException('"' + email + '"' + " is not a valid email");
        }else{
            this.email = email;
        }

        if(age <= 14){ // kontrollerer at age er 15 eller større for, at kunne registrere en bruger
            throw new IllegalArgumentException("You must be 15 years or older");
        }else if(age>110) {
            throw new IllegalArgumentException("Are you really older than 110 years old?\n ﴾͡๏̯͡๏﴿ O'RLY?");
        }
        profiles = new ArrayList<>();
        Profiles firstProfiles = new Profiles(uppercase,age);
        profiles.add(firstProfiles);
        selectedProfile = firstProfiles;

    }

    //TODO find ud af hvilke af disse metoder vi skal bruge :D
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

}
