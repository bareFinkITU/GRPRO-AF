package UserMVC;

import java.util.ArrayList;
import java.util.List;

public class Users { //***DENNE KLASSE SKAL TIL MODEL***

    List<User> users;

    public Users(){
        users = new ArrayList<>();
    }

    public void addUser(User e){
        users.add(e);
    }

    public void removeUser(User e){
        users.remove(e);
    }

    public void getUserr(User e){

    }

    public User getUser(User username) {
        System.out.println(username);
        return username;
    }

    public void getUserInfo(String e){ // henter UserInfo hvis det username man giver den er ens med et username i arraylisten
        for(User u : users){
            if(e.toLowerCase().equals(u.getUsername().toLowerCase())){
                u.show();
            }
        }
    }

    public void registerUser(String name, String username, String password, String email, int age){
        for (User u: users){
            // Evt. skriv at oprettelse af bruger er "case sensitive" (til GUI)
            if (username.toLowerCase().equals(u.getUsername().toLowerCase())) {
                throw new validRegistration(username + " is already taken, try another username");
            }else if(email.toLowerCase().equals(u.getEmail().toLowerCase())){
                throw new validRegistration(email + " is already taken, try another e-mail.");
            }
        }
        if(username != null || email != null) {
            User user = new User(name, username, password, email, age);
            addUser(user);
        }else{
            throw new NullPointerException("Field is empty");
        }
    }

    public User login(String usernameOrEmail, String password){
        //sout("indtast brugernavn/email");
        for (User u: users) {
            if(usernameOrEmail == null){
                throw new NullPointerException("Field is empty");
            }else if(usernameOrEmail.toLowerCase().equals(u.getUsername().toLowerCase())
            || usernameOrEmail.toLowerCase().equals(u.getEmail().toLowerCase())){
                if(password.equals(u.getPassword())){
                    System.out.println("Login Successful");
                    return u;
                } else {
                    throw new loginException();
                }
            }else{
                throw new loginException(usernameOrEmail);
            }
        }
        return null;
    }


}
