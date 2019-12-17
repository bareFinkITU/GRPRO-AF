package SubModel;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String username;
    private String password;
    private String email;
    private String uppercase;
    private List<Profile> profiles;
    private Profile selectedProfile;

    public User(String name, String username, String password, String email, int age){

        if((name.length() > 32 || name.length() < 2) && name.matches("(?=.*[a-z])")){
            throw new IllegalArgumentException("Name requirements: \n" +
                                               "minimum 2 characters long \n" +
                                               "maximum 32 characters long");
        }else{
            this.name = name.trim().replaceAll(" +", " ");
        }
        String uppercase;
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
        Profile firstProfile = new Profile(uppercase,age);
        profiles.add(firstProfile);
        selectedProfile = firstProfile;

    }

    public void addProfile(Profile p){
        profiles.add(p);
    }

    public void removeProfile(Profile p){ //fjerner en profil fra listen af profiles
        profiles.remove(p);
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

    public List<Profile> getProfiles(){
        return profiles;
    }

    public Profile getSelectedProfile(){
        return selectedProfile;
    }

    public void setSelectedProfile(Profile profile){
        selectedProfile = profile;
    }

}
