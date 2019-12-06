package test;

import controller.ContentController;
import model.Content;
import model.Movie;
import model.Show;

import java.io.IOException;
import java.util.ArrayList;

public class BackendTest {
    public static void main(String[] args) throws IOException {
        //initialize content
       ContentController test = ContentController.getInstanceOf();
        //dummy movie and show
        Movie m = new Movie("test", new String[]{"Comedy, Thriller"}, 9.9, null, 2019);
        Show s = new Show("test", new String[]{"Krimi, Drama"}, 9.9, null, "1999-2019", "1-22, 2-12, 3-17, 4-9");
        //initializeContent

        //TESTAREA
        test.searchByRating(9.0);
        test.display();
        System.out.println("=====");
        test.searchByGenre("drama");
        test.display();


        //c.searchByType(2);
    }
}


