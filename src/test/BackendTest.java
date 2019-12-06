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
        ContentController test = new ContentController();
        //dummy movie and show
        Movie m = new Movie("test",new String[]{"Comedy, Thriller"}, 9.9,null, 2019);
        Show s = new Show("test", new String[]{"Krimi, Drama"}, 9.9,null,"1999-2019","1-22, 2-12, 3-17, 4-9");
        //initializeContent
        test.initializeContent();


        //TESTAREA

        test.searchByGenre2("Mystery");
        System.out.println("======\n\n");




        //c.searchByType(2);
    }
}


