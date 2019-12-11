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
        if( name == null ){
            throw new NullPointerException("Please input a name");
        }else if(name.length() > 16){
            throw new IllegalArgumentException("Name can be at most 16 characters long");
        }else{
            this.name = name;
        }
        if(age <= 0){
            throw new IllegalArgumentException("Age must be greater than 0");
        }else if(age > 110 ){
            throw new IllegalArgumentException("Age must be less than 110");
        }else{
            this.age = age;
        }
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

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }




}
