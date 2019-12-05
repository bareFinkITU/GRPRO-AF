package test;

import controller.ContentController;
import model.Content;

import java.io.IOException;
import java.util.ArrayList;

public class searchGenreTest {
    public static void main(String[] args) throws IOException {
        ContentController c = new ContentController();
        c.initializeContent();
        c.searchByGenre("Action");

    }
}


