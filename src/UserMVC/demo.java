package UserMVC;

public class demo {
    public static void main(String[] args) {
        User u = new User("bob","FinkyDinky","123123", "fatherFucker@live.dk",21);
        User j = new User("bob","Flotfyr23","123123", "fatherFucker@live.dk",21);
        Users users = Users.getInstanceOf();
        users.addUser(u);
        users.addUser(j);
        users.login("FinkyDinky","123123");
        users.registerUser("bob","Flotfyr23","123123","fatherFuck@live.dk",99);

    }

}
