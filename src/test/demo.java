package test;

import TODO_CHANGE_MY_NAME.User;
import Model.UserModel;

public class demo {
    public static void main(String[] args) {
        User u = new User("bob","FinkyDinky","123123", "fatherFucker@live.dk",21);
        User j = new User("bob","Flotfyr23","123123", "fatherFucker@live.dk",21);
        UserModel userModel = UserModel.getInstanceOf();
        userModel.addUser(u);
        userModel.addUser(j);
        userModel.login("FinkyDinky","123123");
        userModel.registerUser("bob","Flotfyr23","123123","fatherFuck@live.dk",99);

    }

}
