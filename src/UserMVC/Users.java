package UserMVC;

import java.util.ArrayList;
import java.util.List;

public class Users { //***DENNE KLASSE SKAL TIL MODEL***

    private User selectedUser;
    private List<User> users;
    private static Users instance;

    private Users(){
        users = new ArrayList<>();

        //deafult admin bruger
        users.add(new User("admin" , "admin", "password", "adminpassword@gmail.com",420));

    }
    public static Users getInstanceOf() { //singleton
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public User getSelectedUser(){ //retunere den bruger man er logged ind på
        return selectedUser;
    }

    public void addUser(User e){
        users.add(e);
    }

    public void removeUser(User e){
        users.remove(e);
    }

    public void getUserr(User e){ }

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
            if (username.trim().toLowerCase().equals(u.getUsername().trim().toLowerCase())) {
                //tjekker om brugernavnet allerde eksisterer
                throw new validRegistration(username + " is already taken, try another username");
            }else if(email.toLowerCase().trim().equals(u.getEmail().trim().toLowerCase())){
                //tjekker om email allerede eksisterer
                throw new validRegistration(email + " is already taken, try another e-mail.");
            }
        }
        if(username != null && email != null) {
            //sørger for username og email er forskellig fra null
            User user = new User(name, username, password, email, age);
            addUser(user);
            //hvis dette er sandt opretter vi en ny user objekt og tilføjer den til listen
            for (User a : users) {
                System.out.println(a.getName());
            }
        }else{
            throw new NullPointerException("Field is empty");
        }
    }

    public User login(String usernameOrEmail, String password){
        //sout("indtast brugernavn/email");
        for (User u: users) {
            if(usernameOrEmail == null){
                //den klassiske NullPointerException handling
                throw new NullPointerException("Field is empty");
            }else if(usernameOrEmail.trim().toLowerCase().equals(u.getUsername().trim().toLowerCase())
            || usernameOrEmail.trim().toLowerCase().equals(u.getEmail().trim().toLowerCase())){
                if(password.equals(u.getPassword())){
                    selectedUser = u; //gemmer hvilken bruger man er logget ind på
                    return u;
                } else {
                    throw new loginException("Wrong password!");
                }
            }
        }
        throw new loginException("The following username/e-mail doesnt exist: " + usernameOrEmail);
    }


}
