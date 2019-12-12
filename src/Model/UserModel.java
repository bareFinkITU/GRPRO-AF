package Model;

import Exceptions.IllegalLoginException;
import Exceptions.invalidRegistration;
import TODO_CHANGE_MY_NAME.User;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private User selectedUser;
    private List<User> users;
    private static UserModel instance;

    private UserModel(){
        users = new ArrayList<>();
        //default admin bruger
        //TODO tiføj flere dummy brugere.
        users.add(new User("admin" , "admin", "Password123", "adminpassword@gmail.com",50));

    }
    public static UserModel getInstanceOf() { //singleton
        if (instance == null) {
            instance = new UserModel();
        }
        return instance;
    }

    public User getSelectedUser(){ //retunere den bruger man er logged ind på
        return selectedUser;
    }

    public void addUser(User e){
        users.add(e);
    }

    //TODO implementer metoder?
    public void removeUser(User e){
        users.remove(e);
    }
    public User getUser(User username) {
        return username;
    }

    public void registerUser(String name, String username, String password, String email, int age){
        for (User u: users){
            if (username.trim().toLowerCase().equals(u.getUsername().trim().toLowerCase())) {
                //tjekker om brugernavnet allerde eksisterer
                //TODO skal gribes af oskar
                throw new invalidRegistration(username + " is already taken, try another username");
            }else if(email.toLowerCase().trim().equals(u.getEmail().trim().toLowerCase())){
                //tjekker om email allerede eksisterer
                throw new invalidRegistration(email + " is already taken, try another e-mail.");
            }
        }
        //TODO tjek om det virker
        if(!username.equals("") && !email.equals("")) {
            //sørger for username og email er forskellig fra null, kan en bruger oprettes.
            User user = new User(name, username, password, email, age);
            addUser(user);
        }else{
            //TODO skal gribes af oskar
            throw new NullPointerException("Field is empty");
        }
    }

    public User login(String usernameOrEmail, String password){
        //sout("indtast brugernavn/email");
        for (User u: users) {
            //TODO test denne, er nullpointerexception nødvendig?
            if(usernameOrEmail.equals("")){
                throw new NullPointerException("Field is empty");
            }else if(usernameOrEmail.trim().toLowerCase().equals(u.getUsername().trim().toLowerCase())
            || usernameOrEmail.trim().toLowerCase().equals(u.getEmail().trim().toLowerCase())){
                if(password.equals(u.getPassword())){
                    selectedUser = u; //gemmer hvilken bruger man er logget ind på
                    return u;
                } else {
                    throw new IllegalLoginException();
                }
            }
        }
        throw new IllegalLoginException("The following username/e-mail doesn't exist: " + usernameOrEmail);
    }


}
