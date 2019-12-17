package Model;

import Exceptions.IllegalLoginException;
import Exceptions.invalidRegistration;
import SubModel.User;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private User selectedUser;
    private List<User> users;
    private static UserModel instance;

    private UserModel(){
        users = new ArrayList<>();
        //default admin bruger
        users.add(new User("Bruger1" , "Bruger1", "Password123", "Bruger1@gmail.com",50));
        users.add(new User("Oskar", "Oskar123", "Kode1234", "oskar@gmail.com", 23 ));
        users.add(new User("Frederik", "Frederikfink", "Kode4321", "Frede@gmail.com", 22));
        users.add(new User("Robert", "Rove", "Password321", "Robert@hotmail.com", 22));

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

    public void registerUser(String name, String username, String password, String email, int age) throws invalidRegistration {
        for (User u: users){
            if (username.trim().toLowerCase().equals(u.getUsername().trim().toLowerCase())) {
                //tjekker om brugernavnet allerde eksisterer
                throw new invalidRegistration(username + " is already taken, try another username");
            }else if(email.toLowerCase().trim().equals(u.getEmail().trim().toLowerCase())){
                //tjekker om email allerede eksisterer
                throw new invalidRegistration(email + " is already taken, try another e-mail.");
            }
        }
        if(!username.equals("") && !email.equals("")) {
            //sørger for username og email er forskellig fra null, kan en bruger oprettes.
            User user = new User(name, username, password, email, age);
            addUser(user);
        }else{
            throw new NullPointerException("All fields must be filled");
        }
    }

    public User login(String usernameOrEmail, String password){
        //sout("indtast brugernavn/email");
        for (User u: users) {
            if(usernameOrEmail.equals("")){
                throw new NullPointerException("Pls enter an Username or Email");
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
