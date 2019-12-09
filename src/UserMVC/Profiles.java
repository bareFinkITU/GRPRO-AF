package UserMVC;

import model.Content;

import java.util.ArrayList;
import java.util.List;

public class Profiles {
    private String name;
    private List<Content> favorites; //enkelte profils favorit liste for film/serier
    private int age;

    public Profiles(String name, int age){
        favorites = new ArrayList<Content>();
        this.name = name;
        this.age = age;
    }

    public List<Content> getFavorites(){
        return favorites;
    }

    public void addContent(Content c){
        favorites.add(c);
    }

    public void removeContent(Content c){
        favorites.remove(c);
    }

    public void editProfileName(String s){
        name = s;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }




}
