package test;

import Model.MediaModel;
import TODO_CHANGE_MY_NAME.Movie;
import TODO_CHANGE_MY_NAME.Show;

import java.io.IOException;

public class BackendTest {
    public static void main(String[] args) throws IOException {
        //initialize content
       MediaModel test = MediaModel.getInstanceOf();
        //dummy movie and show
        Movie m = new Movie("test", new String[]{"Comedy, Thriller"}, 9.9, null, 2019);
        Show s = new Show("test", new String[]{"Krimi, Drama"}, 9.9, null, "1999-2019", 1999, 2018, "1-22, 2-12, 3-17, 4-9");
        //initializeContent

        //TESTAREA
        test.searchByTitle("br");
        test.searchForShows();
        test.display();
        System.out.println("----");
        test.searchForMovies();
        test.display();
    }
}


